package com.kosmasfn.movie.ui.model

data class GenreUIModel(
    val genres: List<GenreItemUIModel> = listOf()
){
    data class GenreItemUIModel(
        val id: Int = 0,
        val name: String = ""
    )
}