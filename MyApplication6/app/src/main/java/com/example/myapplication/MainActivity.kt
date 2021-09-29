package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var ed1:EditText
    lateinit var ed2:EditText
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed1=findViewById(R.id.ed1)
        ed1=findViewById(R.id.ed2)
        button=findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(applicationContext,"$ed1  $ed2",Toast.LENGTH_SHORT).show()
        }
    }
}