package com.movies.di

import com.movies.data.remote.ApiConstants
import com.movies.data.remote.IApiServices
import com.movies.data.remote.RetryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json; UTF-8")
                .addHeader("Accept", "application/json")
            chain.proceed(request.build())
        }
            .connectTimeout(ApiConstants.TIMEOUT_SECS, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.TIMEOUT_SECS, TimeUnit.SECONDS)
            .addInterceptor(RetryInterceptor(maxRetries = ApiConstants.MAX_RETRIES))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(ApiConstants.ROOT_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IApiServices {
        return retrofit.create(IApiServices::class.java)
    }
}