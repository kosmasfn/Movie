package com.kosmasfn.movie.di

import android.content.Context
import com.kosmasfn.movie.data.repository.MovieRepository
import com.kosmasfn.movie.data.repository.MovieRepositoryImpl
import com.kosmasfn.movie.data.network.service.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuoteRepository(
        apiService: APIService,
        @ApplicationContext context: Context
    ): MovieRepository =
        MovieRepositoryImpl(context, apiService)

}