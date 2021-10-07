package com.example.callback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Toast.makeText(applicationContext, "On start activity3 ", Toast.LENGTH_SHORT).show()
        Log.d("Activity3", "On start  activity3")

    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "Onresume activity3", Toast.LENGTH_SHORT).show()
        Log.d("Activity3", "Onresume activity3")

    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "Ondestroy activity3", Toast.LENGTH_SHORT).show()
        Log.d("Activity3", "Ondestroy activity3")

    }
}