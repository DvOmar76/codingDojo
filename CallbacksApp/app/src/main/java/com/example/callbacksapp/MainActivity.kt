package com.example.callbacksapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun newpage(view: View) {
        var info = Intent(this, MainActivity2::class.java)

        startActivity(info)

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "OnStop activity 1 ", Toast.LENGTH_SHORT).show()
        Log.d("OnStopActivity1", "OnStop activity 1")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "OnResume activity1", Toast.LENGTH_SHORT).show()
        Log.d("OnResumeActivity1", "OnResume activity1")

    }
}