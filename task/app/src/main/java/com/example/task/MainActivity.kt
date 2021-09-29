package com.example.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var name: TextView
    lateinit var mobile: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        name=findViewById(R.id.name)
        mobile=findViewById(R.id.mobile)
        button.setOnClickListener {
            Toast.makeText(applicationContext, "${name.text} ${mobile.text}", Toast.LENGTH_SHORT).show()
        }
    }
}