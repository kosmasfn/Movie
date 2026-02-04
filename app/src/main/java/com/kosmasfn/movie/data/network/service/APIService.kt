package com.kosmasfn.movie.data.network.service

import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.model.MovieDataModel
import com.kosmasfn.movie.data.model.TrailerDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("genre/movie/list")
    suspend fun fetchGenres(): GenreDataModel

    @GET("discover/movie")
    suspend fun fetchMovieByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genre: String
    ): MovieDataModel

    @GET("movie/{movie_id}/videos")
    suspend fun fetchTrailerMovie(@Path("movie_id") movieId: Int): TrailerDataModel

}