package com.example.spacexproject.di

import android.util.Log
import com.example.spacexproject.model.SpaceModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    //Veriyi sağlayan taraf
    fun provideCapsuleSerial(): SpaceModel {
        val capsuleSerial="some_serial"
        val capsuleId="some_id"
        Log.e("Capsule Serial",capsuleSerial)
        return SpaceModel(capsuleSerial,capsuleId)
    }

}