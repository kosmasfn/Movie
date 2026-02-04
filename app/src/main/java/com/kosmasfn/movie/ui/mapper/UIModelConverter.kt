package com.kosmasfn.movie.ui.mapper

import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.domain.model.MovieDomainModel
import com.kosmasfn.movie.ui.model.GenreUIModel
import com.kosmasfn.movie.ui.model.MovieUIModel

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