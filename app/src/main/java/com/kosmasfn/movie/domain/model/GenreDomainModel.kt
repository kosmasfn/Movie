package com.kosmasfn.movie.domain.model

data class GenreDomainModel(
    val genres: List<GenreItemDomainModel> = listOf()
){
    data class GenreItemDomainModel(
        val id: Int = 0,
        val name: String = ""
    )
}