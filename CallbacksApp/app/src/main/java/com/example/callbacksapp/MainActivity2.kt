package com.example.callbacksapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(applicationContext, "On start activity2 ", Toast.LENGTH_SHORT).show()
        Log.d("OnStartActivity2","On start activity2")

    }
    fun newpage(view: View) {
        var info= Intent(this,MainActivity3::class.java)

        startActivity(info)

    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "OnResume activity 2", Toast.LENGTH_SHORT).show()
        Log.d("OnResumeActivity2","OnResume activity 2")

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "OnStop activity2", Toast.LENGTH_SHORT).show()
        Log.d("OnStopActivity2","OnStop activity 2")

    }
}