package com.example.activity_lifecycle_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("onCreate","from onCreate")
    }
    override fun onStart() {
        super.onStart()
        Log.d("onStart","from onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume","from onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause","from onPause")

    }
    override fun onStop() {
        super.onStop()
        Log.d("onStop","from onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy","from onDestroy")

    }

}