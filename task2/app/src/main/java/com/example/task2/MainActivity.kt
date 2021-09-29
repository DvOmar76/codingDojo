package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var name: EditText
    lateinit var button: Button
    lateinit var listNames: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.rvNames)
        name=findViewById(R.id.edName)
        button=findViewById(R.id.button)
        listNames=ArrayList()
        recyclerView.adapter=recyclerAdapter(this,listNames)
        recyclerView.layoutManager=LinearLayoutManager(this)
        button.setOnClickListener {
            listNames.add("${name.text}")
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.scrollToPosition(listNames.size-1)

        }


    }
}