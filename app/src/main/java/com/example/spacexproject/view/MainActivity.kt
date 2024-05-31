package com.example.spacexproject.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexproject.adapter.RecyclerViewAdapter
import com.example.spacexproject.databinding.ActivityMainBinding
import com.example.spacexproject.model.SpaceModel
import com.example.spacexproject.service.SpaceApi
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {
    lateinit var binding: ActivityMainBinding
    val BASE_URL = "https://api.spacexdata.com/v3/"
    var spaceModels:ArrayList<SpaceModel>?=null
    lateinit var recyclerViewAdapter:RecyclerViewAdapter
    @Inject
    lateinit var capsule_id:SpaceModel
    @Inject
    lateinit var capsule_serial:SpaceModel
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
            Toast.makeText(this,"Clicked:${spaceModel.capsule_id}",Toast.LENGTH_LONG).show()
    }
}