package com.example.spacexproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.spacexproject.R
import com.example.spacexproject.model.SpaceModel

class RecyclerViewAdapter(private val spacexList: ArrayList<SpaceModel>,private val listener: Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(spaceModel: SpaceModel)
    }
    class RowHolder(view:View):RecyclerView.ViewHolder(view){
        private val capsule_id: TextView = view.findViewById(R.id.capsule_id)
        private val capsule_serial: TextView = view.findViewById(R.id.capsule_serial)

        fun bind(spaceModel:SpaceModel,listener: Listener){
            capsule_id.text=spaceModel.capsule_id
            capsule_serial.text=spaceModel.capsule_serial
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
        holder.bind(spacexList[position],listener)
    }
    fun updateSpaceList(newSpaceList:List<SpaceModel>){
        spacexList.clear()
        spacexList.addAll(newSpaceList)
        notifyDataSetChanged()
    }
}