package com.cc.iot_manager_kot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent


class MyAdapter(private val context: Context, private val dataList: List<MyData>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]

        holder.textView1.text = data.text1
        holder.textView2.text = data.text2

        val spinnerAdapter = ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_item,
            listOf("No Selection", "Remove Camera", "Edit Connection Settings", "Set Camera Sensors", "View Camera")
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spinner.adapter = spinnerAdapter
        holder.spinner.setSelection(spinnerAdapter.getPosition(data.spinnerValue))

        // Set up the OnItemSelectedListener for the Spinner
        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Retrieve the selected item from the Spinner
                val selectedItem = parent?.getItemAtPosition(position).toString()

                // Perform the desired action with the selected item
                // print or update other UI elements
                println("Selected item: $selectedItem")

                // Get the associated TextView's text
                val associatedText = data.text1 + ""
                println("Associated text: $associatedText")

                //perform further actions based on the selected item and associated text

                if (selectedItem=="Remove Camera") {
                    DbOpps.removeCameras(associatedText,context)
                }
                else if (selectedItem=="Edit Connection Settings"){
                   DbOpps.getCameraData(associatedText,context)
                    // Create an Intent object
                    val intent = Intent(context, AddCamera::class.java)

                    // Put the string into the Intent using a key-value pair
                    intent.putExtra("CameraName", associatedText)

                    // Start the second activity
                    context.startActivity(intent)
                }
                else if (selectedItem=="View Camera"){

                    DbOpps.getCameraData(associatedText,context)
                    // Create an Intent object
                    val intent = Intent(context, ViewCamera::class.java)
                    // Put the string into the Intent using a key-value pair
                    intent.putExtra("CameraName", associatedText)
                    context.startActivity(intent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
