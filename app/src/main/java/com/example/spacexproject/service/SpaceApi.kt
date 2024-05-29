package com.example.spacexproject.service

import com.example.spacexproject.model.SpaceModel

interface SpaceApi {


    //GET  https://api.spacexdata.com/v3/capsules
    //Base URL https://api.spacexdata.com/v3
    //capsules'
//open source api oldugu için key yoktur.O yuzden burada GET("") seklinde keyi yazmadım
    fun getData() : retrofit2.Call<List<SpaceModel>>
}