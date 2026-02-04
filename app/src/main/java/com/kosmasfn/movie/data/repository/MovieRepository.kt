package com.kosmasfn.movie.data.repository
import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.model.MovieDataModel
import com.kosmasfn.movie.data.model.TrailerDataModel

interface MovieRepository {
    suspend fun fetchGenres(): GenreDataModel
    suspend fun fetchMoviesByGenre(page: Int, genre: String): MovieDataModel
    suspend fun fetchTrailerMovie(movieId: Int): TrailerDataModel
}