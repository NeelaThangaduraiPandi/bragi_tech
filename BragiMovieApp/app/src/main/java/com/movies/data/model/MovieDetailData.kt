package com.movies.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailData(
    val id: Int,

    val title: String,

    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val rating: Float,

    val budget: Long,

    val revenue: Long
)