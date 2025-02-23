package com.movies.domain.repository

import com.movies.data.remote.dto.MoviesListResponse

interface MoviesRepository {
    suspend fun getMoviesList(): MoviesListResponse
}