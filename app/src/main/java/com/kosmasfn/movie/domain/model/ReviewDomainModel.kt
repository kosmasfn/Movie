package com.kosmasfn.movie.domain.model

data class ReviewDomainModel(
    val id: Int = 0,
    val page: Int = 0,
    val results: List<ReviewItemDomainModel> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) {

    data class ReviewItemDomainModel(
        val id: String = "",
        val author: String = "",
        val authorDetails: AuthorDetailsDomainModel = AuthorDetailsDomainModel(),
        val content: String = "",
        val createdAt: String = "",
        val updatedAt: String = "",
        val url: String = ""
    )

    data class AuthorDetailsDomainModel(
        val avatarPath: String = "",
        val name: String = "",
        val rating: Int = 0,
        val username: String = ""
    )
}
