package com.example.spacexproject.view

import SpaceApiService
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.spacexproject.R
import com.example.spacexproject.adapter.RecyclerViewAdapter
import com.example.spacexproject.databinding.ActivityMainBinding
import com.example.spacexproject.model.SpaceModel
import com.example.spacexproject.service.SpacexServiceBuilder
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://api.spacexdata.com/v3/"
    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val viewModel by viewModels <SpacexViewModel>()

    lateinit var binding: ActivityMainBinding
    var spaceModels:ArrayList<SpaceModel>?=null
    lateinit var recyclerViewAdapter:RecyclerViewAdapter

    //Api ile retrofiti birbirine baglamak için bir service olusturdum
    val service=retrofit.create(SpaceApiService::class.java)
    val call =service.getData()
    //requesti async sekilde yollar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }
    fun goToDescription(view:View){
        val fragmentManager=supportFragmentManager
        val fragmentTraction=fragmentManager.beginTransaction()
        val firstFragment=RocketDetailActivity()
        // fragmentTraction.replace()

    }

    private fun loadData() {

        val destinationService=SpacexServiceBuilder.buildService(SpaceApiService::class.java)
        val requestCall=destinationService.getData()

        requestCall.enqueue(object : Callback<List<SpaceModel>>{
            override fun onResponse(call: Call<List<SpaceModel>>, response: Response<List<SpaceModel>>) {
                val rocketList = response.body()
                Log.d("Response","onResponse: ${response.body()}")
                if (response.isSuccessful){
                    if (rocketList != null) {
                        Log.d("Response", "RocketList size : ${rocketList.size}")
                        if (response.isSuccessful){
                            Log.d("Response", "RocketList size : ${rocketList.size}")
                            recyclerViewAdapter.apply {

                               // layoutManager = LinearLayoutManager(this@MainActivity)
                                //adapter = RocketAdapter(response.body()!!)
                            }
                            //rocket_recycler.adapter?.notifyDataSetChanged()
                        }
                        else{
                            Toast.makeText(this@MainActivity, "Something went wrong :( ${response.message()}", Toast.LENGTH_SHORT).show()
                            Log.d("Response", "Failure: ${response.body()}")
                        }
                    }

                }

            }

            override fun onFailure(call: Call<List<SpaceModel>>, t: Throwable) {
                Log.d(TAG, "onFailure ${t.message}")
                Toast.makeText(this@MainActivity, "Something went wrong :( $t", Toast.LENGTH_SHORT).show()
            }

             })

    }
}



