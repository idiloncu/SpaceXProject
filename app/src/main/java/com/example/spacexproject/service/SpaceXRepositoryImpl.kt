package com.example.spacexproject.service

import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor (
    private val apiService: SpaceApiService
) :SpacexRepository{
    override fun getRockets() = apiService.getData()

}