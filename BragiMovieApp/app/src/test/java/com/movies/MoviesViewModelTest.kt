package com.movies

import com.movies.data.model.MovieData
import com.movies.data.model.MovieDetailData
import com.movies.data.remote.IApiServices
import com.movies.data.remote.dto.MoviesListResponse
import com.movies.data.repositoryImpl.MoviesRepositoryImpl
import com.movies.domain.repository.MoviesRepository
import com.movies.domain.useCase.MoviesUseCase
import com.movies.presentation.viewModel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private val mockAPIService: IApiServices = mock()
    private val repository: MoviesRepository = MoviesRepositoryImpl(mockAPIService)
    private val useCase = MoviesUseCase(repository)
    private val movieViewModel = MoviesViewModel(useCase)

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Test
    fun `test getMoviesList returns Success with correct movies list`() = runTest {
        val expectedMovieDataList = listOf(
            MovieData(20, "Inception", "Overview", "", 1.0f),
            MovieData(20, "Interstellar", "Sci-Fi", "", 1.0f)
        )
        val expectedResponse = MoviesListResponse(
            page = 1,
            results = expectedMovieDataList,
            totalResults = 203,
            totalPages = 834
        )

        val mockValidGenreId = "TestGenreId"
        val mockValidMovieId = 20

        val expectedMovieDetail = MovieDetailData(20, "Inception", "Overview", "", 1.0f, 100L, 5000L)

        Mockito.`when`(mockAPIService.getMovieDetailsById(mockValidMovieId))
            .thenReturn(expectedMovieDetail)

        Mockito.`when`(mockAPIService.getMoviesListByGenre(withGenreId = mockValidGenreId))
            .thenReturn(expectedResponse)

        movieViewModel.currentGenreId = mockValidGenreId
        val firstState = movieViewModel.moviesListState.value
        assertTrue(firstState.isLoading)
        delay(500)
        val secondState = movieViewModel.moviesListState.value
        assertTrue(secondState.errorMessage == null)
        assertTrue(secondState.data != null)
    }

     @Test
     fun `test getMoviesList should emit Error when repository throws exception`() = runTest {
         val errorMessage = "Invalid Data"
         val mockInvalidGenreId = "TestGenreId"
         Mockito.`when`(repository.getMoviesList(withGenreId = mockInvalidGenreId))
             .thenThrow(RuntimeException(errorMessage))
         movieViewModel.currentGenreId = mockInvalidGenreId

         val firstState = movieViewModel.moviesListState.value
         assertTrue(firstState.isLoading)
         delay(500)
         val secondState = movieViewModel.moviesListState.value
         assertTrue(secondState.data == null)
         assertTrue(secondState.errorMessage != null)
     }
}