package com.kosmasfn.movie.ui.detail

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.kosmasfn.movie.BuildConfig
import com.kosmasfn.movie.ui.component.ImageCustomView
import com.kosmasfn.movie.ui.component.Loading
import com.kosmasfn.movie.ui.component.TrailersEmptyStateView
import com.kosmasfn.movie.ui.component.YoutubeVideoPlayer
import com.kosmasfn.movie.ui.model.MovieUIModel
import com.kosmasfn.movie.utils.formatDate

@Composable
fun DetailContent(
    context: Context,
    paddingValues: PaddingValues,
    movie: MovieUIModel.MovieItemUIModel,
    viewModel: TrailerViewModel = hiltViewModel()
) {
    viewModel.fetchTrailerMovie(movie.id)

    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = "")
    val trailerId by viewModel.trailerId.collectAsState("")

    val movieItem by remember { mutableStateOf(movie) }

    Box {
        ImageCustomView(
            context,
            Modifier,
            BuildConfig.POSTER_BASE_URL + movie.backdropPath,
            true
        )
        LazyColumn(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.padding(top = 180.dp),
                ) {
                    ImageCustomView(
                        context,
                        Modifier
                            .height(260.dp)
                            .width(140.dp),
                        BuildConfig.POSTER_BASE_URL + movie.posterPath
                    )
                    Column(modifier = Modifier.padding(top = 68.dp, start = 20.dp)) {
                        Text(
                            modifier = Modifier.weight(0.9f),
                            text = movieItem.title,
                            textAlign = TextAlign.Start,
                            fontSize = 26.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        SummaryItemView(
                            movieItem.voteAverage,
                            movieItem.voteCount,
                            movieItem.releaseDate.formatDate(),
                            movieItem.originalLanguage
                        )
                    }
                }
                OverviewItemView(movieItem.overview)
                Text(
                    modifier = Modifier.padding(top = 40.dp),
                    text = "Trailers",
                    fontSize = 26.sp
                )
                if (isLoading) Loading()
                if (trailerId.isNotEmpty()) {
                    YoutubeVideoPlayer(trailerId)
                } else {
                    TrailersEmptyStateView()
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}