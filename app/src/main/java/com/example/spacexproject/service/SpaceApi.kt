package com.example.spacexproject.service

import com.example.spacexproject.model.SpaceModel
import retrofit2.Call
import retrofit2.http.GET

interface SpaceApi{


    //GET  https://api.spacexdata.com/v3/capsules
    //Base URL https://api.spacexdata.com/v3/
    //capsules'
//open source api oldugu için key yoktur.O yuzden burada GET("") seklinde keyi yazmadım
    @GET("https://api.spacexdata.com/v3/")
    fun getData() : Call<List<SpaceModel>>
}