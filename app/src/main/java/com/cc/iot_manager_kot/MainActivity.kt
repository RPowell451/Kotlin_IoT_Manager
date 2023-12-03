package com.cc.iot_manager_kot

import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout

import com.cc.iot_manager_kot.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : NavigationPane() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)

        val mAuth = FirebaseAuth.getInstance()
        val userRef = FirebaseDatabase.getInstance().reference.child("Users")

        userID =  mAuth.currentUser!!.email

        userRef.addValueEventListener((object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val firstName = p0.child(mAuth.currentUser!!.uid).child("UserData").child("firstName").value
                val lastName = p0.child(mAuth.currentUser!!.uid).child("UserData").child("lastName").value
                user_name = "$firstName $lastName"
                binding.bodyText.text = ("Hello " + user_name + ", welcome to our app! Please select an Iot to use by clicking on the navigation pane in upper left corner. We hope you enjoy our app.")
            }
        }))
    }
}