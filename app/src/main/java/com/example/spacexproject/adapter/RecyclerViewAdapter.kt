package com.example.spacexproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.spacexproject.R
import com.example.spacexproject.model.SpaceModel

class RecyclerViewAdapter(private val spacexList: ArrayList<SpaceModel>,private val listener: Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(spaceModel: SpaceModel)
    }
    class RowHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(spaceModel:SpaceModel,listener: Listener){
            itemView.setOnClickListener{
                listener.onItemClick(spaceModel)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.rows,parent,false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return spacexList.count()

    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        //hangi itemın hangi veriyi gosterecegi yazılır



    }
}