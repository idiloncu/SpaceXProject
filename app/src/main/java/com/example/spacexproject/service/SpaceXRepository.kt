package com.example.spacexproject.service

import SpaceApiService
import javax.inject.Inject

class SpaceXRepository @Inject constructor(
    private val apiService: SpaceApiService
) {
    suspend fun getRockets() = apiService.getData()

}