package com.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movies.presentation.navigation.RootNavGraph.FILTER_SCREEN
import com.movies.presentation.navigation.RootNavGraph.MOVIES_LIST_SCREEN
import com.movies.presentation.ui.screen.FiltersScreen
import com.movies.presentation.ui.screen.MoviesScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    val keyCurrentGenreId = "current_genre_id"

    NavHost(
        navController = navController,
        startDestination = MOVIES_LIST_SCREEN
    ) {
        composable(MOVIES_LIST_SCREEN) { MoviesScreen(navController) }

        composable(
            "$FILTER_SCREEN{$keyCurrentGenreId}",
            arguments = listOf(navArgument(keyCurrentGenreId) {
                type = NavType.StringType
                nullable = true})
        ) { backStackEntry ->
            val currentGenreId = backStackEntry.arguments?.getString(keyCurrentGenreId)
            FiltersScreen(navController, currentGenreId)
        }
    }
}

object RootNavGraph {
    const val MOVIES_LIST_SCREEN = "MOVIES_LIST_SCREEN"
    const val FILTER_SCREEN = "FILTER_SCREEN/"
}
