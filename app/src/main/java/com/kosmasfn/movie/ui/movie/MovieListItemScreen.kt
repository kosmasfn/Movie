package com.kosmasfn.movie.ui.movie

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kosmasfn.movie.ui.component.ItemMovieCard
import com.kosmasfn.movie.ui.MainViewModel
import com.kosmasfn.movie.utils.showMessage

@Composable
fun MovieListItemScreen(viewModel: MainViewModel, genre: String) {

    val context = LocalContext.current
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = "")
    val movies by viewModel.movies.collectAsState()
    val totalPages by viewModel.totalPages.collectAsState(initial = Integer.MAX_VALUE)

    var page by remember { mutableIntStateOf(1) }
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }.collect { lastVisibleIndex ->
            if (lastVisibleIndex != null && lastVisibleIndex >= movies.size - 6 && !isLoading) {
                page++
                if (page > totalPages) return@collect
                viewModel.fetchMoviesByGenre(page, genre)
            }
        }
    }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        state = gridState,
    ) {
        movies.forEach { item ->
            item {
                ItemMovieCard(context, item)
            }
        }

        if (isLoading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }

    if (errorMessage.isNotEmpty()) {
        context.showMessage(errorMessage)
    }
}
