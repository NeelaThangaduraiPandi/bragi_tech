package com.movies.domain.useCase

import com.movies.domain.repository.MoviesRepository
import com.movies.domain.state.ResponseState
import com.movies.domain.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MovieGenresUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(): Flow<ResponseState<List<Pair<Int, String>>>> = flow {
        try {
            val response = repository.getMoviesGenres()
            val genres = response.genres.map {
                Pair(it.id, it.name)
            }
            emit(ResponseState.Success(data = genres))
        } catch (ioException: IOException) {
            emit(
                ResponseState.Error(
                    message = ioException.localizedMessage ?: Constants.GENERIC_ERROR_MESSAGE
                )
            )
        }
    }
}