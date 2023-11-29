package com.cc.iot_manager_kot

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddThermostat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_thermostat)

        val editTextThermostatName = findViewById<EditText>(R.id.editTextThermostatName)
        val editTextRoom = findViewById<EditText>(R.id.editTextRoom)
        val buttonConnectThermostat = findViewById<Button>(R.id.buttonConnectThermostat)

        buttonConnectThermostat.setOnClickListener {
            val thermostatName = editTextThermostatName.text.toString()
            val room = editTextRoom.text.toString()

            if (thermostatName.isEmpty() || room.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Handle the connection of the thermostat here
                Toast.makeText(this, "Connecting thermostat $thermostatName in room $room", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
