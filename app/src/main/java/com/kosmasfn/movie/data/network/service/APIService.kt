package com.kosmasfn.movie.data.network.service

import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.model.MovieDataModel
import com.kosmasfn.movie.data.model.TrailerDataModel
import retrofit2.http.Body
import retrofit2.http.GET
interface APIService {

    @GET("genre/movie/list")
    suspend fun fetchGenres(): GenreDataModel

    @GET("discover/movie")
    suspend fun fetchMovieByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genre: String
    ): MovieDataModel
}