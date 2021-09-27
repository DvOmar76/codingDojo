package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name=findViewById<EditText>(R.id.name)
        val location=findViewById<EditText>(R.id.location)
        val mobile=findViewById<EditText>(R.id.mobile)
        val toa=findViewById<Button>(R.id.toa)
        val tv=findViewById<Button>(R.id.Tv)
        val go=findViewById<Button>(R.id.go)
        var textview=findViewById<TextView>(R.id.textView)
        var inputN :String
        var inputL:String
        var inputM :String

        tv.setOnClickListener{
            inputN=name.text.toString()
            inputL=location.text.toString()
            inputM=mobile.text.toString()
            textview.setText("$inputN | $inputL  | $inputM ")
        }
        toa.setOnClickListener{
            inputN=name.text.toString()
            inputL=location.text.toString()
            inputM=mobile.text.toString()
            val toast = Toast.makeText(applicationContext, "$inputN | $inputL  | $inputM ", Toast.LENGTH_SHORT)
            toast.show()
        }
        go.setOnClickListener{
            inputN=name.text.toString()
            inputL=location.text.toString()
            inputM=mobile.text.toString()
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("inputL",inputL)
            intent.putExtra("inputN",inputN)
            intent.putExtra("inputM",inputM)
            startActivity(intent)
        }

    }
}