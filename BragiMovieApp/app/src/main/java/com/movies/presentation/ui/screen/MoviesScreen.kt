package com.movies.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.movies.R
import com.movies.presentation.navigation.RootNavGraph.FILTER_SCREEN
import com.movies.presentation.viewModel.MoviesViewModel

@Composable
fun MoviesScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: MoviesViewModel = hiltViewModel()
    val uiState = viewModel.moviesListState.collectAsState().value

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.errorMessage != null) {
            NoDataFoundText(uiState.errorMessage)
        }
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            if (uiState.data != null) {
                Box(modifier = Modifier
                        .fillMaxSize()
                    .background(Color.LightGray),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    MoviesGrid(context = context, uiState.data)
                }
            } else {
                NoDataFoundText(stringResource(R.string.no_data_found))
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                .padding(end = 30.dp, bottom = 50.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(FILTER_SCREEN) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    content = {
                        Icon(Icons.Default.Edit, contentDescription = "Filter")
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewMoviesScreen() {
    MoviesScreen(navController = rememberNavController())
}