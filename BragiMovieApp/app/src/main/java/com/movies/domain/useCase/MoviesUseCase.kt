package com.movies.domain.useCase

import com.movies.data.remote.ApiConstants
import com.movies.domain.model.Movie
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.state.ResponseState
import com.movies.domain.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(withGenreId: String?): Flow<ResponseState<List<Movie>>> = flow {
        try {
            delay(500) // Added just to experience the loading state
            val response = repository.getMoviesList(withGenreId)
            val moviesList = response.results.map {
                val imageURL = ApiConstants.IMAGE_ROOT_URL + it.posterPath
                Movie(
                    it.id,
                    it.title,
                    it.overview,
                    imageURL = imageURL,
                    it.rating,
                    0.0f,
                    0.0f
                )
            }
            emit(ResponseState.Success(data = moviesList))
        } catch (ioException: IOException) {
            emit(
                ResponseState.Error(
                    message = ioException.localizedMessage ?: Constants.GENERIC_ERROR_MESSAGE
                )
            )
        }
    }
}