package com.kosmasfn.movie.domain.usecase

import com.kosmasfn.movie.domain.common.Resource
import com.kosmasfn.movie.data.repository.MovieRepository
import com.kosmasfn.movie.data.mapper.toDomainModel
import com.kosmasfn.movie.domain.model.GenreDomainModel
import com.kosmasfn.movie.domain.model.MovieDomainModel
import com.kosmasfn.movie.domain.model.TrailerDomainModel
import com.kosmasfn.movie.util.replaceURL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCaseImpl @Inject constructor(private val repository: MovieRepository) : UseCase {

    override suspend fun fetchGenres(): Flow<Resource<GenreDomainModel>> = flow {
        try {
            emit(Resource.loading())
            emit(Resource.success(repository.fetchGenres().toDomainModel()))
        } catch (e: Throwable) {
            emit(Resource.error(Throwable(e.localizedMessage?.replaceURL())))
        }
    }

    override suspend fun fetchMoviesByGenre(
        page: Int,
        genre: String
    ): Flow<Resource<MovieDomainModel>> = flow {
        try {
            emit(Resource.loading())
            emit(Resource.success(repository.fetchMoviesByGenre(page, genre).toDomainModel()))
        } catch (e: Throwable) {
            emit(Resource.error(Throwable(e.localizedMessage?.replaceURL())))
        }
    }

    override suspend fun fetchTrailerMovie(movieId: Int): Flow<Resource<TrailerDomainModel>> =
        flow {
            try {
                emit(Resource.loading())
                emit(Resource.success(repository.fetchTrailerMovie(movieId).toDomainModel()))
            } catch (e: Throwable) {
                emit(Resource.error(Throwable(e.localizedMessage?.replaceURL())))
            }
        }
}