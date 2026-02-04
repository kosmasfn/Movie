package com.kosmasfn.movie.domain.usecase

import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.domain.model.GenreDomainModel
import kotlinx.coroutines.flow.Flow

interface UseCase {
    suspend fun fetchGenres(): Flow<Resource<GenreDomainModel>>
}