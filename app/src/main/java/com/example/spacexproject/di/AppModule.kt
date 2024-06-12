package com.example.spacexproject.di

import android.content.Context
import com.example.spacexproject.service.SpaceApiService
import com.example.spacexproject.HiltApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule() {
    @Provides
    @Singleton
    fun providesOkHttpCache(@ApplicationContext context: Context
    ): Cache {
        return Cache(context.cacheDir, OK_HTTP_CACHE_SIZE.toLong())
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .create()
    }

    @Provides
    @Singleton
    fun providesApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url: HttpUrl = chain.request().url
                .newBuilder()
                .build()
            val request: Request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache, apiKeyInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieService(retrofit: Retrofit): SpaceApiService {
        return retrofit.create(SpaceApiService::class.java)
    }

    companion object {
        private const val OK_HTTP_CACHE_SIZE = 10 * 1024 * 1024
        private const val GSON_DATE_FORMAT = "yyyy-MM-dd"
    }
}






