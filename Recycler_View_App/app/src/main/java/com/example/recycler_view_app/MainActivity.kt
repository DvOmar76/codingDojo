package com.example.recycler_view_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var list= mutableListOf<String>()
    lateinit var editText: EditText
    lateinit var button: Button
    private lateinit var cLMain:ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText=findViewById(R.id.editTextTextPersonName)
        button=findViewById(R.id.button)
        list= mutableListOf()
        cLMain=findViewById(R.id.cLMain)
        recyclerView.adapter=RecyclerViewAdapter(this,list)
        recyclerView.layoutManager= LinearLayoutManager(this)
        button.setOnClickListener {
            addMessage()
        }


    }
    fun addMessage(){
        val msg = editText.text.toString()
        if(msg.isEmpty()){
            Snackbar.make(cLMain, "Please enter some text", Snackbar.LENGTH_LONG).show()

        }else{
            list.add(msg)
            editText.text.clear()
            editText.clearFocus()
        }
    }

}