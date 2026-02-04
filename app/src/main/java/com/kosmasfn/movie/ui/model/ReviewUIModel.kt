package com.kosmasfn.movie.ui.model

data class ReviewUIModel(
    val id: Int = 0,
    val page: Int = 0,
    val results: List<ReviewItemUIModel> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) {

    data class ReviewItemUIModel(
        val id: String = "",
        val author: String = "",
        val authorDetails: AuthorDetailsUIModel = AuthorDetailsUIModel(),
        val content: String = "",
        val createdAt: String = "",
        val updatedAt: String = "",
        val url: String = ""
    )

    data class AuthorDetailsUIModel(
        val avatarPath: String = "",
        val name: String = "",
        val rating: Int = 0,
        val username: String = ""
    )
}
