package com.kosmasfn.movie.data.network.service

import com.kosmasfn.movie.data.model.GenreDataModel
import retrofit2.http.GET
interface APIService {

    @GET("genre/movie/list")
    suspend fun fetchGenres(): GenreDataModel
}