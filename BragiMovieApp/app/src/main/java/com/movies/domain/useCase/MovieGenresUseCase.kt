package com.movies.domain.useCase

import com.movies.domain.model.MovieGenre
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.state.ResponseState
import com.movies.domain.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MovieGenresUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(): Flow<ResponseState<List<MovieGenre>>> = flow {
        try {
            val response = repository.getMoviesGenres()
            val genres = response.genres.map {
                MovieGenre(it.id, it.name)
            }
            emit(ResponseState.Success(data = genres))
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
}