package com.movies.presentation.ui.screen.support

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TitleText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = text,
        color = Color.Black,
        maxLines = 2,
        softWrap = true,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Medium
    )
}

@Composable
@Preview
fun PreviewTitleText() {
    TitleText(
        text = "Title"
    )
}