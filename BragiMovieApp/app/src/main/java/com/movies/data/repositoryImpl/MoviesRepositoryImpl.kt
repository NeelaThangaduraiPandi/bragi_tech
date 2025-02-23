package com.movies.data.repositoryImpl

import com.movies.data.remote.IApiServices
import com.movies.data.remote.dto.MoviesListResponse
import com.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiServices: IApiServices): MoviesRepository {
    override suspend fun getMoviesList(): MoviesListResponse {
        return apiServices.getMoviesList()
    }
}