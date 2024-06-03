package com.example.spacexproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexproject.R
import com.example.spacexproject.model.SpaceModel
import com.example.spacexproject.view.RocketDetailActivity
import com.squareup.picasso.Picasso


class RecyclerViewAdapter(private val spacexList: ArrayList<SpaceModel>): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    class RowHolder(view:View):RecyclerView.ViewHolder(view){
     //   private val capsule_id: TextView = view.findViewById(R.id.capsule_id)
       // private val capsule_serial: TextView = view.findViewById(R.id.capsule_serial)
        var rocketPicture=itemView.findViewById<ImageView>(R.id.rocketPicture)
        var rocketName=itemView.findViewById<ImageView>(R.id.rocketName)

        fun bind(data:SpaceModel){

            Picasso.get().load(data.flickr_images.get(0)).into(rocketPicture)
            itemView.setOnClickListener{
                val rocketIntent=Intent(itemView.context,RocketDetailActivity::class.java)
                rocketIntent.putExtra(RocketDetailActivity.EXTRA_ROCKET,data)
                itemView.context.startActivity(rocketIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.rocket_item,parent,false)
        return RowHolder(view)
    }
    override fun getItemCount(): Int {
        return spacexList.count()
    }
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        //hangi itemın hangi veriyi gosterecegi yazılır
        holder.bind(spacexList[position])
    }
}


