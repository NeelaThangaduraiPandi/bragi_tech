package com.movies.domain.repository

import com.movies.data.model.MovieDetailData
import com.movies.data.remote.dto.MovieGenresListResponse
import com.movies.data.remote.dto.MoviesListResponse

interface MoviesRepository {
    suspend fun getMoviesList(withGenreId: String?): MoviesListResponse

    suspend fun getMoviesGenres(): MovieGenresListResponse

    suspend fun getMovieDetailsById(movieId: Int): MovieDetailData
}