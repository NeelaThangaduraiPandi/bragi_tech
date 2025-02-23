package com.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.movies.presentation.navigation.SetUpNavGraph
import com.movies.presentation.ui.theme.BragiMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BragiMovieAppTheme {
                val navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    }
}