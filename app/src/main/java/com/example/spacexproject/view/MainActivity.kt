package com.example.spacexproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexproject.R
import com.example.spacexproject.adapter.RecyclerViewAdapter
import com.example.spacexproject.databinding.ActivityMainBinding
import com.example.spacexproject.model.SpaceModel
import com.example.spacexproject.service.SpaceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://api.spacexdata.com/v3"
    private var spaceModels:ArrayList<SpaceModel>?=null
    private lateinit var recyclerViewAdapter:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(arrayListOf(),this)
        binding.recyclerView.adapter = recyclerViewAdapter

        loadData()

    }
    //https://api.spacexdata.com/v4/capsules
    private fun loadData(){
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Api ile retrofiti birbirine baglamak için bir service olusturdum
        val service=retrofit.create(SpaceApi::class.java)
        val call =service.getData()
        //requesti async sekilde yollar
        call.enqueue(object :Callback<List<SpaceModel>> {
            override fun onFailure(call: Call<List<SpaceModel>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(
                call: Call<List<SpaceModel>>,
                response: Response<List<SpaceModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        //eger response.body()? null gelmedıyse bu blogu yapar
                        spaceModels=ArrayList(it)
                        recyclerViewAdapter.updateSpaceList(spaceModels!!)
                        for(xmodel : SpaceModel in spaceModels!!){
                            println(xmodel.capsule_id)
                            println(xmodel.capsule_serial)
                        }
                    }
                }
            }
        })
    }

    override fun onItemClick(spaceModel: SpaceModel) {
        println("Clicked: ${spaceModel.capsule_id}")
    }
}