package com.movies.di

import com.movies.data.remote.IApiServices
import com.movies.data.repositoryImpl.MoviesRepositoryImpl
import com.movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(apiServices: IApiServices): MoviesRepository {
        return MoviesRepositoryImpl(apiServices)
    }
}