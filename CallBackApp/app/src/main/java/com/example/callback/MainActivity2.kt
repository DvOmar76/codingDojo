package com.example.callback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(applicationContext, "On resume activity2 ", Toast.LENGTH_SHORT).show()

    }

    fun newpage(view: View) {
        var info= Intent(this,MainActivity::class.java)
        Toast.makeText(applicationContext, "Ondestroy activity2", Toast.LENGTH_SHORT).show()

        startActivity(info)

    }
}