package com.kosmasfn.movie.data.model

import com.google.gson.annotations.SerializedName

data class ReviewDataModel(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: List<ReviewItemDataModel>? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null
) {

    data class ReviewItemDataModel(
        @SerializedName("author")
        val author: String? = null,

        @SerializedName("author_details")
        val authorDetails: AuthorDetailsDataModel? = null,

        @SerializedName("content")
        val content: String? = null,

        @SerializedName("created_at")
        val createdAt: String? = null,

        @SerializedName("id")
        val id: String? = null,

        @SerializedName("updated_at")
        val updatedAt: String? = null,

        @SerializedName("url")
        val url: String? = null
    )

    data class AuthorDetailsDataModel(
        @SerializedName("avatar_path")
        val avatarPath: String? = null,

        @SerializedName("name")
        val name: String? = null,

        @SerializedName("rating")
        val rating: Int? = null,

        @SerializedName("username")
        val username: String? = null
    )
}
