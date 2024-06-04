package com.example.spacexproject.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.spacexproject.R
import com.example.spacexproject.model.SpaceModel

val TAG="Rocket Detail Activity"
class RocketDetailActivity : AppCompatActivity() {

    private lateinit var description: TextView
    private lateinit var successRate: TextView
    private lateinit var stages: TextView
    private lateinit var firstLaunch: TextView
    private lateinit var costPerLaunch: TextView
    private lateinit var payload: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rocket_description)

        description = findViewById(R.id.description)
        successRate = findViewById(R.id.successRate)
        stages = findViewById(R.id.stages)
       firstLaunch = findViewById(R.id.firstLaunch)
       costPerLaunch = findViewById(R.id.costPerLaunch)
        payload = findViewById(R.id.payload)

        val rocket = intent.getParcelableExtra<SpaceModel>(EXTRA_ROCKET)
        Log.d(TAG, "onCreate:  + $rocket")
        if (rocket != null) {
            bind(rocket)
        }
    }

     fun bind(data: SpaceModel) {
        Log.d(TAG, "Data: " + data)

         description.text = data.description ?: ":("
        successRate.text = ("Success Rate: " + data.success_rate_pct.toString()) ?: ":("
        stages.text = ("Number of Stages: " + data.stages.toString()) ?: ":("
        firstLaunch.text = ("First Launch Date: " + data.first_flight) ?: ":("
        costPerLaunch.text = ("Cost for Every Launch: " + data.cost_per_launch.toString()) ?: ":("
        payload.text = ("Payload Weight: " + data.payload_weights.toString()) ?: ":("


    }

    companion object {
        val EXTRA_ROCKET = "rocket"
    }
}


