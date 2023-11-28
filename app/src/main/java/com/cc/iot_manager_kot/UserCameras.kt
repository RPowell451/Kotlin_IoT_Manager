package com.cc.iot_manager_kot

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserCameras : NavigationPane() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_cameras)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

        // Get the current user ID
        val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        // Reference to the Cameras node for the current user
        val camerasReference = databaseReference.child("Users").child(userId).child("Cameras")

        val dataList = mutableListOf<MyData>()

        // Add a ValueEventListener to retrieve the camera names
        camerasReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (cameraSnapshot in snapshot.children) {
                    val cameraName = cameraSnapshot.key

                    //add camera name to list
                    println(cameraName)
                    dataList.add(MyData(cameraName, "Text2-3", "Option 3"))
                }
                val adapter = MyAdapter(this@UserCameras, dataList)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)
    }
}







/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UserCameras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_cameras)
    }
}
 */