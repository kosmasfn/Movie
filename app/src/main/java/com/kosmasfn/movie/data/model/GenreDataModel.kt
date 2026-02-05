package com.kosmasfn.movie.data.model

import com.google.gson.annotations.SerializedName

data class GenreDataModel(
    @SerializedName("genres")
    val genres: List<GenreItemDataModel>?
) {
    data class GenreItemDataModel(
        @SerializedName("id")
        val id: Int?, val name: String?
    )
}