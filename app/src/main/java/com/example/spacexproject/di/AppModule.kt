package com.example.spacexproject.di

import SpaceApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object AppModule {
    private val interceptor:HttpLoggingInterceptor=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }
    private val client:OkHttpClient=OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    //Veriyi sağlayan taraf
    @Provides
    @Singleton
    fun provideApi():SpaceApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.spacexdata.com/v3/")
            .client(client)
            .build()
            .create(SpaceApiService::class.java)
    }

}


