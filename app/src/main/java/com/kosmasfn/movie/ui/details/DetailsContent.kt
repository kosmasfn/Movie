package com.kosmasfn.movie.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.kosmasfn.movie.ui.details.review.LoadingFooter
import com.kosmasfn.movie.ui.details.review.ReviewCard
import com.kosmasfn.movie.ui.model.MovieUIModel
import com.kosmasfn.movie.utils.formatDate
import com.kosmasfn.movie.utils.showMessage

@Composable
fun DetailContent(
    paddingValues: PaddingValues,
    movie: MovieUIModel.MovieItemUIModel,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.fetchTrailerMovie(movie.id)

    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = "")
    val trailerId by viewModel.trailerId.collectAsState("")

    val isLoadingReviews by viewModel.isLoadingReviews.collectAsState(initial = false)
    val errorMessageReviews by viewModel.errorMessageReviews.collectAsState(initial = "")
    val reviews by viewModel.reviews.collectAsState()
    val totalPages by viewModel.totalPages.collectAsState(Integer.MAX_VALUE)

    val movieItem by remember { mutableStateOf(movie) }

    val listState = rememberLazyListState()
    var page by remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit) {
        viewModel.fetchReviews(movie.id, page)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null && lastVisibleIndex >= reviews.lastIndex - 3 && !isLoading) {
                    page++
                    if (page > totalPages) return@collect
                    viewModel.fetchReviews(movie.id, page)
                }
            }
    }


    Box {
        ImageCustomView(
            context, Modifier, BuildConfig.POSTER_BASE_URL + movie.backdropPath, true
        )
        LazyColumn(
            state = listState,
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
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 40.dp), text = "Trailers", fontSize = 26.sp
                )
                if (isLoading) Loading()
                if (trailerId.isNotEmpty()) {
                    YoutubeVideoPlayer(trailerId)
                } else {
                    TrailersEmptyStateView()
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 40.dp), text = "Reviews", fontSize = 26.sp
                )
                HorizontalDivider()
            }

            if (reviews.isEmpty()) {
                item {
                    Spacer(Modifier.height(8.dp))
                    Text("No reviews yet ....")
                }
            }

            items(reviews.size) { index ->
                ReviewCard(reviews[index])
            }

            if (isLoadingReviews) item {
                LoadingFooter()
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

    if(errorMessage.isNotEmpty()) context.showMessage(errorMessage)
    if(errorMessageReviews.isNotEmpty()) context.showMessage(errorMessageReviews)
}