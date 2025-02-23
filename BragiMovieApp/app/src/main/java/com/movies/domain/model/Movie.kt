package com.movies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val imageURL: String,
    val rating: Float,
    val revenue: Float,
    val budget: Float
)