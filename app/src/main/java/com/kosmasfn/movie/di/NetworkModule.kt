package com.kosmasfn.movie.di

import com.kosmasfn.movie.data.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): NetworkInterceptor = NetworkInterceptor()

    @Provides
    @Singleton
    fun provideServiceInterceptor(
        interceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}