package com.example.spacexproject.di

import com.example.spacexproject.service.SpaceApiService
import com.example.spacexproject.service.SpaceXRepositoryImpl
import com.example.spacexproject.service.SpacexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule  {
    @Provides
    @Singleton
    fun provideSpaceXRepository(api: SpaceApiService):SpacexRepository{
        return SpaceXRepositoryImpl(apiService = api)

    }
}