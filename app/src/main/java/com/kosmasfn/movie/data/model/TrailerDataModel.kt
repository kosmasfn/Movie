package com.kosmasfn.movie.data.model

import com.google.gson.annotations.SerializedName

data class TrailerDataModel(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("results")
    val results: List<TrailerItemDataModel>? = null
) {

    data class TrailerItemDataModel(

        @SerializedName("key")
        val key: String? = null,

        @SerializedName("type")
        val type: String? = null
    )
}