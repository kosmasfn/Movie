package com.kosmasfn.movie.data.repository
import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.model.MovieDataModel

interface MovieRepository {
    suspend fun fetchGenres(): GenreDataModel
    suspend fun fetchMoviesByGenre(page: Int, genre: String): MovieDataModel
}