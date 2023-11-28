package com.cc.iot_manager_kot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.gson.GsonBuilder

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

import com.cc.iot_manager_kot.databinding.ActivityAddCameraBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AddCamera : NavigationPane() {

    private lateinit var binding: ActivityAddCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCameraBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        // Retrieve the string from the Intent
        val receivedString = intent.getStringExtra("CameraName")
        if( receivedString != "") {
            binding.cameraName.setText(receivedString)
            binding.cameraLocation.setText(DbOpps.cameraLocation)
            binding.ipAddress.setText(DbOpps.IPAddress)
            binding.portNumber.setText(DbOpps.portNumber)
            DbOpps.clearCameraSettings()
        }

        val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)

        binding.addCameraButton.setOnClickListener {
            registerNewCamera()
        }

    }

    private fun registerNewCamera() {

        val newCameraName = binding.cameraName.text.toString()
        val newCameraLocation = binding.cameraLocation.text.toString()
        val newIPAddress = binding.ipAddress.text.toString()
        val newPortNumber = binding.portNumber.text.toString()

        if (TextUtils.isEmpty(newCameraName)) {
            Toast.makeText(this, "Please enter a Camera Name", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(newCameraLocation)) {
            Toast.makeText(this, "Please enter a Camera Location", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(newIPAddress)) {
            Toast.makeText(this, "Please enter an IP Address", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(newPortNumber)) {
            Toast.makeText(this, "Please enter a Port Number", Toast.LENGTH_LONG).show()
            return
        }

        val mAuth = FirebaseAuth.getInstance()

        val database = FirebaseDatabase.getInstance()
        val ref = database.reference.child("Users")



                val userID = mAuth.currentUser!!.uid
                //val currentUser = ref.child(userID)
                val currentUser = ref.child(userID).child("Cameras").child(newCameraName)

                currentUser.child("Location").setValue(newCameraLocation)
                currentUser.child("IP Address").setValue(newIPAddress)
                currentUser.child("Port Number").setValue(newPortNumber)

                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()

                val homeIntent = Intent(this, MainActivity::class.java)
                startActivity(homeIntent)




    }

}

/*
package com.cc.iot_manager_kot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddCamera : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_camera)
    }
}
 */