package com.cc.iot_manager_kot


import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView1: TextView = itemView.findViewById(R.id.textView1)
    val textView2: TextView = itemView.findViewById(R.id.textView2)
    val spinner: Spinner = itemView.findViewById(R.id.spinner)
}
