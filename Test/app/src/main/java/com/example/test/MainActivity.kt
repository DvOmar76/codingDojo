package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var nameText:EditText
    lateinit var addbtn: Button
    var names= ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myRecyclerView = findViewById<RecyclerView>(R.id.rc)
        myRecyclerView.adapter = adapter( names)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        nameText=findViewById(R.id.textView2)
        addbtn=findViewById(R.id.button)
        addbtn.setOnClickListener(){
            val mes=nameText.text.toString()
            names.add(mes)
            nameText.text.clear()
            nameText.clearFocus()

          myRecyclerView.adapter?.notifyDataSetChanged()


        }
    }
}