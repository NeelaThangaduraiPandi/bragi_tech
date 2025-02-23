package com.movies.data.repositoryImpl

import com.movies.data.model.MovieDetailData
import com.movies.data.remote.IApiServices
import com.movies.data.remote.dto.MovieGenresListResponse
import com.movies.data.remote.dto.MoviesListResponse
import com.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiServices: IApiServices): MoviesRepository {
    override suspend fun getMoviesList(withGenreId: String?): MoviesListResponse {
        return apiServices.getMoviesListByGenre(withGenreId = withGenreId)
    }

    override suspend fun getMoviesGenres(): MovieGenresListResponse {
        return apiServices.getMovieGenres()
    }

    override suspend fun getMovieDetailsById(movieId: Int): MovieDetailData {
        return apiServices.getMovieDetailsById(movieId = movieId)
    }
}