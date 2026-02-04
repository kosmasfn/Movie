package com.kosmasfn.movie.ui.genre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.kosmasfn.movie.ui.model.GenreUIModel

@Composable
fun GenreListScreen(
    onGenreClick: (GenreUIModel.GenreItemUIModel) -> Unit
) {
    val viewModel = hiltViewModel<GenreViewModel>()
    viewModel.fetchGenres()
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = "")
    val genres by viewModel.genres.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Movie Genres",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(genres.genres.size) { index ->
                GenreChip(
                    genre = genres.genres[index],
                    onClick = { onGenreClick(genres.genres[index]) })
            }
        }
    }
}