package com.kosmasfn.movie.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDataModel(
    @SerialName("genres")
    val genres: List<GenreItemDataModel>?
) {
    @Serializable
    data class GenreItemDataModel(
        @SerialName("id")
        val id: Int?, val name: String?
    )
}