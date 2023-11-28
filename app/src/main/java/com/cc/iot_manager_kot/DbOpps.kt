package com.cc.iot_manager_kot

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import android.content.Context
import android.content.Intent

class DbOpps {
    companion object {
        // Static function
        fun removeCameras(cameraToRemove: String, context:Context) {

            // Reference to the Firebase Database
            val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

            // Get the current user ID
            val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""

            // Reference to the Cameras node for the current user
            val camerasReference = databaseReference.child("Users").child(userId).child("Cameras").child(cameraToRemove)

            camerasReference.removeValue()
            DbOpps.refreshActivity(context)
        }

        fun getCameraData(cameraToFind : String, context:Context){
            val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

            // Get the current user ID
            val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""

            // Reference to the Cameras node for the current user
            val camerasReference = databaseReference.child("Users").child(userId).child("Cameras").child(cameraToFind)

            val dataList = mutableListOf<MyData>()

            // Add a ValueEventListener to retrieve the camera names
            camerasReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (cameraSnapshot in snapshot.children) {
                        val cameraSettings = cameraSnapshot.value

                        //add camera name to list
                        println(cameraSettings)
                       if (cameraSnapshot.key == "IP Address")
                           IPAddress = cameraSettings.toString()
                       else if (cameraSnapshot.key == "Location")
                           cameraLocation = cameraSettings.toString()
                       else if (cameraSnapshot.key == "Port Number")
                           portNumber = cameraSettings.toString()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the onCancelled event (even if it's empty for now)
                }

            })

        }

        fun clearCameraSettings(){
            DbOpps.cameraLocation = ""
            DbOpps.IPAddress = ""
            DbOpps.portNumber = ""
        }

        fun refreshActivity(context: Context) {
            val intent = Intent(context, UserCameras::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        // static property
        var cameraLocation: String? = ""
        var IPAddress: String? = ""
        var portNumber: String? = ""
    }



}