package com.kosmasfn.movie.ui.mapper

import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.domain.model.MovieDomainModel
import com.kosmasfn.movie.domain.model.ReviewDomainModel
import com.kosmasfn.movie.ui.model.GenreUIModel
import com.kosmasfn.movie.ui.model.MovieUIModel
import com.kosmasfn.movie.ui.model.ReviewUIModel

fun GenreDomainModel.toUIModel(): GenreUIModel =
    GenreUIModel(this.genres.map { it.toUIModel() })

fun GenreDomainModel.GenreItemDomainModel.toUIModel(): GenreUIModel.GenreItemUIModel =
    GenreUIModel.GenreItemUIModel(
        id = this.id, name = this.name
    )

fun MovieDomainModel.toUIModel(): MovieUIModel = MovieUIModel(
    this.page,
    this.results.map { it.toUIModel() },
    this.totalPages,
    this.totalResults,
)

fun MovieDomainModel.MovieItemDomainModel.toUIModel(
    isFavPage: Boolean = false
): MovieUIModel.MovieItemUIModel =
    MovieUIModel.MovieItemUIModel(
        this.adult,
        this.backdropPath,
        this.genreIds,
        this.id,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount,
        isFavorite = isFavPage
    )

fun ReviewDomainModel.toUIModel(): ReviewUIModel = ReviewUIModel(
    id = this.id,
    page = this.page,
    results = this.results.map { it.toUIModel() },
    totalPages = this.totalPages,
    totalResults = this.totalResults,
)

fun ReviewDomainModel.ReviewItemDomainModel.toUIModel(): ReviewUIModel.ReviewItemUIModel =
    ReviewUIModel.ReviewItemUIModel(
        id = this.id,
        author = this.author,
        authorDetails = this.authorDetails.toUIModel(),
        content = this.content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        url = this.url,
    )


fun ReviewDomainModel.AuthorDetailsDomainModel.toUIModel(): ReviewUIModel.AuthorDetailsUIModel =
    ReviewUIModel.AuthorDetailsUIModel(
        avatarPath = this.avatarPath,
        name = this.name,
        rating = this.rating,
        username = this.username,
    )