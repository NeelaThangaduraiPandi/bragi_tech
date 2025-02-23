package com.movies.presentation.state

object UIState {
    data class MoviesUIState<T>(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val data: T? = null
    )
}