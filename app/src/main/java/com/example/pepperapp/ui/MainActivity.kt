package com.example.pepperapp.ui

import android.os.Bundle
import android.widget.Toast
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy
import android.view.View
import com.example.pepperapp.R

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {

    private var qiContext: QiContext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Hide the status bar and navigation bar immediately
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        
        // Hide the Pepper QiSDK Speech Bar (the grey box at the top)
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE)

        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        } else {
            // Permission already granted
            QiSDK.register(this, this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, now we can register
                QiSDK.register(this, this)
            } else {
                // Permission denied
                Toast.makeText(this, "Permission de stockage nécessaire pour QiSDK", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        this.qiContext = qiContext
    }

    override fun onRobotFocusLost() {
        qiContext = null
    }

    override fun onRobotFocusRefused(reason: String?) {
        Toast.makeText(this, "Focus refusé : $reason", Toast.LENGTH_LONG).show()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    fun getQiContext(): QiContext? = qiContext

}
