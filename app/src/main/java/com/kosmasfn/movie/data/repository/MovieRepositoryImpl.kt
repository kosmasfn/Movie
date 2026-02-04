package com.kosmasfn.movie.data.repository

import android.content.Context
import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.network.service.APIService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val context: Context,
    private val apiService: APIService
) : MovieRepository {

    override suspend fun fetchGenres(): GenreDataModel {
        return apiService.fetchGenres()
    }
}