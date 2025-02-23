package com.movies.presentation.state

data class UIState<T>(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val data: T? = null
)