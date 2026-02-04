package com.kosmasfn.movie.data.mapper

import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.data.model.MovieDataModel
import com.kosmasfn.movie.data.model.ReviewDataModel
import com.kosmasfn.movie.data.model.TrailerDataModel
import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.domain.model.MovieDomainModel
import com.kosmasfn.movie.domain.model.ReviewDomainModel
import com.kosmasfn.movie.domain.model.TrailerDomainModel

fun GenreDataModel.toDomainModel(): GenreDomainModel =
    GenreDomainModel(this.genres?.map { it.toDomainModel() } ?: listOf())

fun GenreDataModel.GenreItemDataModel.toDomainModel(): GenreDomainModel.GenreItemDomainModel =
    GenreDomainModel.GenreItemDomainModel(
        id = this.id ?: 0, name = this.name ?: ""
    )

fun MovieDataModel.toDomainModel(): MovieDomainModel = MovieDomainModel(
    this.page ?: 0,
    this.results?.map { it.toDomainModel() } ?: listOf(),
    this.total_pages ?: 0,
    this.total_results ?: 0,
)

fun MovieDataModel.MovieItemDataModel.toDomainModel(): MovieDomainModel.MovieItemDomainModel =
    MovieDomainModel.MovieItemDomainModel(
        this.adult ?: false,
        this.backdrop_path ?: "",
        this.genre_ids ?: listOf(),
        this.id ?: 0,
        this.original_language ?: "",
        this.original_title ?: "",
        this.overview ?: "",
        this.popularity ?: 0.0,
        this.poster_path ?: "",
        this.release_date ?: "",
        this.title ?: "",
        this.video ?: false,
        this.vote_average ?: 0.0,
        this.vote_count ?: 0
    )

fun TrailerDataModel.toDomainModel(): TrailerDomainModel = TrailerDomainModel(
    this.id ?: 0,
    this.results?.map { it.toDomainModel() } ?: listOf(),
)

fun TrailerDataModel.TrailerItemDataModel.toDomainModel(): TrailerDomainModel.TrailerItemDomainModel =
    TrailerDomainModel.TrailerItemDomainModel(
        this.key ?: "",
        this.type ?: "",
    )

fun ReviewDataModel.toDomainModel(): ReviewDomainModel = ReviewDomainModel(
    id = this.id ?: 0,
    page = this.page ?: 0,
    results = this.results?.map { it.toDomainModel() } ?: listOf(),
    totalPages = this.totalPages ?: 0,
    totalResults = this.totalResults ?: 0,
)

fun ReviewDataModel.ReviewItemDataModel.toDomainModel(): ReviewDomainModel.ReviewItemDomainModel =
    ReviewDomainModel.ReviewItemDomainModel(
        id = this.id ?: "",
        author = this.author ?: "",
        authorDetails = this.authorDetails?.toDomainModel()
            ?: ReviewDomainModel.AuthorDetailsDomainModel(),
        content = this.content ?: "",
        createdAt = this.createdAt ?: "",
        updatedAt = this.updatedAt ?: "",
        url = this.url ?: "",
    )


fun ReviewDataModel.AuthorDetailsDataModel.toDomainModel(): ReviewDomainModel.AuthorDetailsDomainModel =
    ReviewDomainModel.AuthorDetailsDomainModel(
        avatarPath = this.avatarPath ?: "",
        name = this.name ?: "",
        rating = this.rating ?: 0,
        username = this.username ?: "",
    )
