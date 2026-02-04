package com.kosmasfn.movie.data.network

import com.kosmasfn.movie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.BEARER_TOKEN}")
            .build()
        val response = chain.proceed(request)
        if (response.code == 401) {
            response.close()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.BEARER_TOKEN}")
                .build()
            return chain.proceed(newRequest)
        }
        return response
    }
}