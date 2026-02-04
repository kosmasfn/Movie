package com.kosmasfn.movie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.kosmasfn.movie.R

@Composable
fun TrailersEmptyStateView() {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.img_empty_state),
        contentDescription = stringResource(id = R.string.description),
        contentScale = ContentScale.FillWidth,
    )
    Text(
        text = "Upps.... Trailers Not Found",
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        color = Color.Gray
    )
}