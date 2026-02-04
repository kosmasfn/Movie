package com.kosmasfn.movie.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerDataModel(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("results")
    val results: List<TrailerItemDataModel>? = null
) {

    @Serializable
    data class TrailerItemDataModel(

        @SerialName("key")
        val key: String? = null,

        @SerialName("type")
        val type: String? = null
    )
}