package com.example.callback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun newpage(view: View) {
        var info= Intent(this,MainActivity2::class.java)
        Toast.makeText(applicationContext, "onStop activity 1 ", Toast.LENGTH_SHORT).show()

        startActivity(info)

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onStop activity 2 ", Toast.LENGTH_SHORT).show()

    }
}