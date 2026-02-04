package com.kosmasfn.movie.domain.model

data class TrailerDomainModel(
    val id: Int = 0,
    val results: List<TrailerItemDomainModel> = listOf()
) {
    data class TrailerItemDomainModel(
        val key: String = "",
        val type: String = ""
    )
}