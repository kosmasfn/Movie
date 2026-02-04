package com.kosmasfn.movie.ui.component

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kosmasfn.movie.R

@Composable
fun ImageCustomView(
    context: Context,
    modifier: Modifier,
    url: String,
    isBackDrop: Boolean = false
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(context)
            .placeholder(if (isBackDrop) R.drawable.img_backdrop_default else R.drawable.img_poster_default)
            .data(url)
            .diskCacheKey(url)
            .memoryCacheKey(url)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}