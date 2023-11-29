package com.cc.iot_manager_kot

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdjustThermostat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adjust_thermostat)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val increaseButton = findViewById<Button>(R.id.increaseButton)
        val decreaseButton = findViewById<Button>(R.id.decreaseButton)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Handle progress change
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Handle start of tracking touch
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Handle stop of tracking touch
                Toast.makeText(this@AdjustThermostat, "Temperature set to ${seekBar.progress}°", Toast.LENGTH_SHORT).show()
            }
        })

        increaseButton.setOnClickListener {
            val currentProgress = seekBar.progress
            if (currentProgress < seekBar.max) {
                seekBar.progress = currentProgress + 1
            }
        }

        decreaseButton.setOnClickListener {
            val currentProgress = seekBar.progress
            if (currentProgress > 0) {
                seekBar.progress = currentProgress - 1
            }
        }
    }
}
