package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{
            intent= Intent(this,MainActivity2::class.java)
            intent.putExtra("name",edName.text.toString())
            intent.putExtra("location",edLocation.text.toString())
            startActivity(intent)
        }
    }
}