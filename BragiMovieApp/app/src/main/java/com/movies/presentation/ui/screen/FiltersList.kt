package com.movies.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.movies.domain.model.MovieGenre
import com.movies.presentation.ui.helper.Keys.KEY_GENRE_ID
import com.movies.presentation.ui.helper.Keys.KEY_GENRE_NAME

@Composable
fun FiltersList(
    navController: NavHostController,
    currentGenreId: String?,
    genres: List<MovieGenre>
) {
    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp)
    ) {
        items(genres) {
            val genreIdAsString = it.id.toString()
            val isSelected = genreIdAsString == currentGenreId
            val color = if (isSelected) Color.DarkGray else Color.LightGray
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = color),
                onClick = {
                    navigateBackToMoviesScreen(navController, it)
                }) {
                Text(color = Color.White, text = it.name)
            }
        }
    }
}

private fun navigateBackToMoviesScreen(
    navController: NavHostController,
    selectedGenre: MovieGenre
) {
    navController.previousBackStackEntry?.savedStateHandle?.set(KEY_GENRE_ID, selectedGenre.id.toString())
    navController.previousBackStackEntry?.savedStateHandle?.set(KEY_GENRE_NAME, selectedGenre.name)
    navController.popBackStack()
}