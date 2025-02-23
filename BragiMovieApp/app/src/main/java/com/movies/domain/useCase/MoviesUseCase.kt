package com.movies.domain.useCase

import com.movies.data.model.MovieDetailData
import com.movies.data.remote.ApiConstants
import com.movies.domain.model.Movie
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.state.ResponseState
import com.movies.domain.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(withGenreId: String?): Flow<ResponseState<List<Movie>>> = flow {
        try {
            val response = repository.getMoviesList(withGenreId)
            val moviesList = response.results.map {
                val detailData = getMovieDetails(it.id)
                val imageURL = ApiConstants.IMAGE_ROOT_URL + it.posterPath

                Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    imageURL = imageURL,
                    rating = it.rating,
                    revenue = formatToMillions(detailData.revenue),
                    budget = formatToMillions(detailData.budget)
                )
            }
            emit(ResponseState.Success(data = moviesList))
        } catch (exception: Exception) {
            val errorMessage = when(exception) {
                is IOException, is IllegalStateException -> {
                    Constants.GENERIC_ERROR_MESSAGE
                }
                else -> {
                    exception.localizedMessage ?: Constants.GENERIC_ERROR_MESSAGE
                }
            }

            emit(ResponseState.Error(message = errorMessage))
        }
    }

    private suspend fun getMovieDetails(movieId: Int): MovieDetailData {
        return repository.getMovieDetailsById(movieId = movieId)
    }

    private fun formatToMillions(number: Long): String {
        if (number < 1_000_000) return number.toString()
        val millionValue = number / 1_000_000.0
        return String.format(Locale.US, "%.02f M", millionValue)
    }
}