package com.kosmasfn.movie.ui.mapper

import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.ui.model.GenreUIModel

fun GenreDomainModel.toUIModel(): GenreUIModel =
    GenreUIModel(this.genres.map { it.toUIModel() })

fun GenreDomainModel.GenreItemDomainModel.toUIModel(): GenreUIModel.GenreItemUIModel =
    GenreUIModel.GenreItemUIModel(
        id = this.id, name = this.name
    )