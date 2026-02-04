package com.kosmasfn.movie.data.repository
import com.kosmasfn.movie.data.model.GenreDataModel

interface MovieRepository {
    suspend fun fetchGenres(): GenreDataModel
}