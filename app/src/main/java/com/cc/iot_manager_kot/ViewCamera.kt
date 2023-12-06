package com.cc.iot_manager_kot

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.drawerlayout.widget.DrawerLayout

class ViewCamera : NavigationPane() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_camera)

        val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)

        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient() // This is important to prevent the URL from opening in an external browser

        // Enable JavaScript (if needed)
        // Enable JavaScript (if needed)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.clearCache(true)
        webView.clearHistory()

        val videoURL = "http://" + DbOpps.IPAddress + ":" + DbOpps.portNumber + "/video_feed"
        DbOpps.clearCameraSettings()
        webView.loadUrl(videoURL)
    }
}