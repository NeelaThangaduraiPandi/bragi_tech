package com.movies.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.domain.model.MovieGenre
import com.movies.domain.state.ResponseState
import com.movies.domain.useCase.MovieGenresUseCase
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
class FiltersViewModel @Inject constructor(private val genreUseCase: MovieGenresUseCase) :
    ViewModel() {

    private val _genresListState = MutableStateFlow(UIState<List<MovieGenre>>())
    val genresListState: StateFlow<UIState<List<MovieGenre>>> =
        _genresListState.asStateFlow()

    init {
        getMovieGenres()
    }

    private fun getMovieGenres() {
        _genresListState.update { UIState(isLoading = true) }
        genreUseCase().onEach { result ->
            when (result) {
                is ResponseState.Success -> {
                    _genresListState.update {
                        UIState(isLoading = false, data = result.data)
                    }
                }

                is ResponseState.Error -> {
                    _genresListState.update {
                        UIState(
                            isLoading = false,
                            errorMessage = result.message ?: Constants.GENERIC_ERROR_MESSAGE
                        )
                    }
                }

            }
        }.launchIn(viewModelScope)
    }
}