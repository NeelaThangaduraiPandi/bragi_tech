package com.movies.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.movies.R
import com.movies.domain.model.Movie
import com.movies.presentation.ui.helper.LoadImage
import com.movies.presentation.ui.screen.support.BodyText
import com.movies.presentation.ui.screen.support.TitleText

@Composable
fun MoviesGrid(movies: List<Movie>) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(movies) { _, item ->
            Card(
                modifier = Modifier
                    .size(width = 100.dp, height = 230.dp)
                    .padding(PaddingValues(vertical = 3.dp, horizontal = 3.dp)),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    LoadImage(context = context, imageUrl = item.imageURL)

                    TitleText(text = item.title)
                    Spacer(modifier = Modifier.height(5.dp))
                    BodyText(text = stringResource(R.string.rating_prefix) + item.rating)
                    BodyText(text = stringResource(R.string.revenue_prefix) + item.revenue)
                    BodyText(text = stringResource(R.string.budget_prefix) + item.budget)
                }
            }
        }
    }
}