package com.cc.iot_manager_kot

import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout

import com.cc.iot_manager_kot.databinding.ActivityMainBinding

class MainActivity : NavigationPane() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)

    }
}