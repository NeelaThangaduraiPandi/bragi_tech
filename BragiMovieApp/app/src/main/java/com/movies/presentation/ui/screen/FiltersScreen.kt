package com.movies.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.movies.R
import com.movies.presentation.viewModel.FiltersViewModel

@Composable
fun FiltersScreen(navController: NavHostController, currentGenreId: String? = null) {
    val viewModel: FiltersViewModel = hiltViewModel()
    val uiState = viewModel.genresListState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(width = 24.dp, height = 24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Text(
                text = stringResource(R.string.filters),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row {
            if (uiState.errorMessage != null) {
                NoDataFoundText(uiState.errorMessage)
            }
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (uiState.data != null) {
                    FiltersList(navController, currentGenreId, genres = uiState.data)
                } else {
                    NoDataFoundText(stringResource(R.string.no_data_found))
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewFiltersScreen() {
    FiltersScreen(navController = rememberNavController())
}