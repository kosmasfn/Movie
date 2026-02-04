package com.kosmasfn.movie.data.mapper

import com.kosmasfn.movie.data.model.GenreDataModel
import com.kosmasfn.movie.domain.model.GenreDomainModel

fun GenreDataModel.toDomainModel(): GenreDomainModel =
    GenreDomainModel(this.genres?.map { it.toDomainModel() } ?: listOf())

fun GenreDataModel.GenreItemDataModel.toDomainModel(): GenreDomainModel.GenreItemDomainModel =
    GenreDomainModel.GenreItemDomainModel(
        id = this.id ?: 0, name = this.name ?: ""
    )
