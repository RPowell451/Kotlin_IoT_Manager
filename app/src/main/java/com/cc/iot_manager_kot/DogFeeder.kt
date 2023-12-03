package com.cc.iot_manager_kot

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.*

class DogFeeder : NavigationPane() {

    private val feedingTimes = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_feeder)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, feedingTimes)

        val listViewTimes: ListView = findViewById(R.id.listViewTimes)
        listViewTimes.adapter = adapter

        val btnAddTime: Button = findViewById(R.id.btnAddTime)
        btnAddTime.setOnClickListener {
            showTimePickerDialog()
        }

        loadFeedingTimesFromDatabase()

        listViewTimes.setOnItemClickListener {_, _, position, _ ->
            showDeleteConfirmationDialog(position)
        }

        val mDrawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)

    }

    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val time = String.format("%02d:%02d", hourOfDay, minute)
                feedingTimes.add(time)
                adapter.notifyDataSetChanged()
                updateFirebaseDatabase(feedingTimes)
            },
            hour,
            minute,
            android.text.format.DateFormat.is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    private fun updateFirebaseDatabase(feedingTimes: List<String>) {
        val mAuth = FirebaseAuth.getInstance()
        val userUid = mAuth.currentUser!!.uid

        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("users/$userUid/feedingTimes")

        reference.setValue(feedingTimes)
    }

    private fun loadFeedingTimesFromDatabase() {
        val mAuth = FirebaseAuth.getInstance()
        val userUid = mAuth.currentUser!!.uid

        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("users/$userUid/feedingTimes")

        // Add a listener to fetch the data from the database
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                feedingTimes.clear() // Clear existing data
                for (snapshot in dataSnapshot.children) {
                    val time = snapshot.getValue(String::class.java)
                    time?.let { feedingTimes.add(it) }
                }
                adapter.notifyDataSetChanged() // Refresh the ListView
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
            }
        })
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirm Delete")
        alertDialogBuilder.setMessage("Are you sure you want to delete this time?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            removeTime(position)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.create().show()
    }

    private fun removeTime(position: Int) {
        if (position in 0 until feedingTimes.size) {
            feedingTimes.removeAt(position)
            adapter.notifyDataSetChanged()
            updateFirebaseDatabase(feedingTimes)
        }
    }
}
