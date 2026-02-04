package com.kosmasfn.movie.domain.usecase

import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface UseCase {
    suspend fun fetchGenres(): Flow<Resource<GenreDomainModel>>
    suspend fun fetchMoviesByGenre(page: Int, genre: String): Flow<Resource<MovieDomainModel>>
}