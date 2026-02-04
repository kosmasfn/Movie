package com.kosmasfn.movie.di

import com.kosmasfn.movie.data.repository.MovieRepository
import com.kosmasfn.movie.domain.usecase.UseCase
import com.kosmasfn.movie.domain.usecase.UseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Reusable
    fun provideUseCase(repository: MovieRepository): UseCase = UseCaseImpl(repository)

}