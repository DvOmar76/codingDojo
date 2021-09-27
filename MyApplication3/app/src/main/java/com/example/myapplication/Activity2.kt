package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val textView=findViewById<TextView>(R.id.textView2)
       val inputN=intent.getStringExtra("inputN")
       val inputL=intent.getStringExtra("inputL")
       val inputM=intent.getStringExtra("inputM")

        textView.setText(" $inputN | $inputL  | $inputM ")
    }
}