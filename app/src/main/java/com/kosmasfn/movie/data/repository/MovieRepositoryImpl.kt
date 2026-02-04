package com.kosmasfn.movie.data.repository

import android.content.Context
import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.model.MovieDataModel
import com.kosmasfn.movie.data.model.ReviewDataModel
import com.kosmasfn.movie.data.model.TrailerDataModel
import com.kosmasfn.movie.data.network.service.APIService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val context: Context,
    private val apiService: APIService
) : MovieRepository {

    override suspend fun fetchGenres(): GenreDataModel {
        return apiService.fetchGenres()
    }

    override suspend fun fetchMoviesByGenre(page: Int, genre: String): MovieDataModel {
        return apiService.fetchMovieByGenre(page, genre)
    }

    override suspend fun fetchTrailerMovie(movieId: Int): TrailerDataModel {
        return apiService.fetchTrailerMovie(movieId)
    }

    override suspend fun fetchReviews(movieId: Int, page: Int): ReviewDataModel {
        return apiService.fetchReviews(movieId, page)
    }
}