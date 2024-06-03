package com.example.spacexproject.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpacexServiceBuilder {
    //OkHttpClient,Http isteklerini yapmak için kullanılır.
    private const val URL= "https://api.spacexdata.com/v3/"
    private val okHttp= OkHttpClient.Builder()

    //Retrofitin instance ını oluşturmak için bir builder kullanılır.
    //Json verilerini serialize ve deserialize etmek için GsonConverterFactory ve client kullanılır.
    private val builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //Retrofitin instance ını olusturdum
    private val retrofit = builder.build()

    //serviceType Retrofitin bir metodudur. create ile verilen arayuzu alır (serviceType) istenen api isteklerini gerçekleştirir.
    fun <T> buildService(serviceType:Class<T>):T {
        return retrofit.create(serviceType)
    }


}