package com.movies.presentation.ui.helper

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.movies.R

@Composable
fun LoadImage(context: Context, imageUrl: String?) {
    val imageURL = if (imageUrl.isNullOrEmpty()) R.drawable.ic_placeholder else imageUrl
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        model = ImageRequest.Builder(context)
            .data(imageURL)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .crossfade(true)
            .scale(Scale.FILL)
            .build(),
        contentDescription = stringResource(R.string.movie_poster),
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
@Preview
fun PreviewLoadImage() {
    LoadImage(
        context = LocalContext.current,
        imageUrl = ""
    )
}