package com.kosmasfn.movie.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieUIModel(
    val page: Int = 0,
    val results: List<MovieItemUIModel> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) : java.io.Serializable {

    @Serializable
    data class MovieItemUIModel(
        val adult: Boolean = false,
        val backdropPath: String = "",
        val genreIds: List<Int> = listOf(),
        val id: Int = 0,
        val originalLanguage: String = "",
        val originalTitle: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        val posterPath: String = "",
        val releaseDate: String = "",
        val title: String = "",
        val video: Boolean = false,
        val voteAverage: Double = 0.0,
        val voteCount: Int = 0,
        var isFavorite: Boolean = false
    ) : java.io.Serializable

}