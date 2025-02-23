package com.movies.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FiltersList(navController: NavHostController, currentGenreId: String?, genres: List<Pair<Int, String>>) {
    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp)
    ) {
        items(genres) {
            val genreIdAsString = it.first.toString()
            val isSelected = genreIdAsString == currentGenreId
            val color = if (isSelected) Color.DarkGray else Color.LightGray
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = color),
                onClick = {
                    navigateBackToMoviesScreen(navController, genreIdAsString)
            }) {
                Text(color = Color.White, text = it.second)
            }
        }
    }
}

private fun navigateBackToMoviesScreen(navController: NavHostController, selectedGenreId: String?) {
    navController.previousBackStackEntry?.savedStateHandle?.set("result", selectedGenreId)
    navController.popBackStack()
}