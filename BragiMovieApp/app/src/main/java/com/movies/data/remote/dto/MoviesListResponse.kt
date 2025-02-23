package com.movies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.movies.data.model.MovieData

data class MoviesListResponse(
    val page: Int,
    val results: List<MovieData>,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName("total_results")
    val totalResults: Long
)