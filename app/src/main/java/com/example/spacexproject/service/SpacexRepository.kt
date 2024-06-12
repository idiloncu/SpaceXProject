package com.example.spacexproject.service

import com.example.spacexproject.model.SpaceModel
import retrofit2.Call

interface SpacexRepository {
    fun getRockets(): Call<List<SpaceModel>>

}