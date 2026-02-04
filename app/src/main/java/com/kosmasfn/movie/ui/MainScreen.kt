package com.kosmasfn.movie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.kosmasfn.movie.ui.component.Loading
import com.kosmasfn.movie.ui.genre.GenreViewModel
import com.kosmasfn.movie.ui.genre.GenreTabs
import com.kosmasfn.movie.ui.movie.MovieListScreen

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    viewModel: GenreViewModel = hiltViewModel()
) {
    val genreState by viewModel.genre.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState(false)
    val errorMessage by viewModel.errorMessage.collectAsState("")

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.fetchGenres()
    }

    LaunchedEffect(selectedTabIndex, genreState.genres) {
        genreState.genres.getOrNull(selectedTabIndex)?.let { genre ->
            viewModel.fetchMoviesByGenre(
                page = 1,
                genre = genre.id.toString()
            )
        }
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        if (genreState.genres.isNotEmpty()) {
            GenreTabs(
                genres = genreState.genres.map { it.name },
                selectedIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )
            MovieListScreen(genre = genreState.genres[selectedTabIndex].id.toString())
        }

        if (isLoading) Loading()

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
