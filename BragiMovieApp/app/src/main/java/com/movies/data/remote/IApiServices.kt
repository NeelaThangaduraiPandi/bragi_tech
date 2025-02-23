package com.movies.data.remote

import com.movies.data.model.MovieDetailData
import com.movies.data.remote.dto.MovieGenresListResponse
import com.movies.data.remote.dto.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiServices {
    @GET("discover/movie")
    suspend fun getMoviesListByGenre(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") withGenreId: String?
    ): MoviesListResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY
    ): MovieGenresListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = ApiConstants.API_KEY
    ): MovieDetailData
}