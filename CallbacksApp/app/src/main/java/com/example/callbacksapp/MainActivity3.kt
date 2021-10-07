package com.example.callbacksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Toast.makeText(applicationContext, "On start activity3 ", Toast.LENGTH_SHORT).show()
        Log.d("OnStartActivity3","On start activity3")
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "OnResume activity3", Toast.LENGTH_SHORT).show()
        Log.d("OnResumeActivity3","OnResume activity 3")

    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "OnStop activity3", Toast.LENGTH_SHORT).show()
        Log.d("OnStopActivity3","OnStop activity 3")

    }
}