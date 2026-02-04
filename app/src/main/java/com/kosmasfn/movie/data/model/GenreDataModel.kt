package com.kosmasfn.movie.data.model

data class GenreDataModel(
    val genres: List<GenreItemDataModel>?
) {
    data class GenreItemDataModel(
        val id: Int?, val name: String?
    )
}