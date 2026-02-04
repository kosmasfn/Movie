package com.kosmasfn.movie.domain.usecase

import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.data.repository.MovieRepository
import com.kosmasfn.movie.data.mapper.toDomainModel
import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.util.replaceURL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class UseCaseImpl @Inject constructor(private val repository: MovieRepository) : UseCase {

    override suspend fun fetchGenres(): Flow<Resource<GenreDomainModel>> = flow {
        try {
            emit(Resource.loading())
            emit(Resource.success(repository.fetchGenres().toDomainModel()))
        } catch (e: Throwable) {
            emit(Resource.error(Throwable(e.localizedMessage?.replaceURL())))
        }
    }
}