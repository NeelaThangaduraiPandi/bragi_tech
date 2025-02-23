package com.movies.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.domain.model.Movie
import com.movies.domain.state.ResponseState
import com.movies.domain.useCase.MoviesUseCase
import com.movies.domain.util.Constants
import com.movies.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    private val _moviesListState = MutableStateFlow(UIState.MoviesUIState<List<Movie>>())
    val moviesListState: StateFlow<UIState.MoviesUIState<List<Movie>>> =
        _moviesListState.asStateFlow()

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        _moviesListState.update { UIState.MoviesUIState(isLoading = true) }
        moviesUseCase().onEach { result ->
            when (result) {
                is ResponseState.Success -> {
                    _moviesListState.update {
                        UIState.MoviesUIState(isLoading = false, data = result.data)
                    }
                }

                is ResponseState.Error -> {
                    _moviesListState.update {
                        UIState.MoviesUIState(
                            isLoading = false,
                            errorMessage = result.message ?: Constants.GENERIC_ERROR_MESSAGE
                        )
                    }
                }

            }
        }.launchIn(viewModelScope)
    }
}