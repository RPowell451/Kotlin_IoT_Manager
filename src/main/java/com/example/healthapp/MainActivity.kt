package com.example.healthapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var heartRate = 0
    private var stepCount = 0

    private lateinit var tvHeartRate: TextView
    private lateinit var tvStepCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        tvHeartRate = findViewById(R.id.tvHeartRate)
        tvStepCount = findViewById(R.id.tvStepCount)

        val btnMeasureHeartRate: Button = findViewById(R.id.btnMeasureHeartRate)
        val btnCountSteps: Button = findViewById(R.id.btnCountSteps)

        btnMeasureHeartRate.setOnClickListener {
            measureHeartRate()
        }

        btnCountSteps.setOnClickListener {
            countSteps()
        }

    }

    private fun measureHeartRate() {
        heartRate = (60..220).random()
        updateHeartRate()
    }

    private fun countSteps() {
        stepCount += (100..500).random()
        updateStepCount()
    }

    private fun updateHeartRate() {
        tvHeartRate.text = "Your current Heart Rate is : $heartRate bpm"
    }

    private fun updateStepCount() {
        tvStepCount.text = "Your current steps are $stepCount steps"
    }
}

