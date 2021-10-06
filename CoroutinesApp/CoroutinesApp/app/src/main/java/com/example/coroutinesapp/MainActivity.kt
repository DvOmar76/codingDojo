package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    val adviceUrl = "https://api.adviceslip.com/advice"
    var pause=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            pause=false
            requestApi()
        }
        button2.setOnClickListener {
            pause=true
        }

    }

    private fun requestApi() {
        CoroutineScope(Dispatchers.IO).launch {

            val data = async {getRandomData()}.await()
            if(!pause&&data.isNotEmpty()) updataData(data)

        }

    }

    private suspend fun updataData(data: String) {
        withContext(Dispatchers.Main)
        {
            val jsonObject = JSONObject(data)
            val slip = jsonObject.getJSONObject("slip")
            val id = slip.getInt("id")
            val advice = slip.getString("advice")
            textView.text = advice
            requestApi()
        }
    }

    private fun getRandomData() :String {
        var response=""
        try {
            response = URL(adviceUrl).readText(Charsets.UTF_8)


        }catch (e: Exception)
        {
            println("Error $e")

        }
        return response

    }

}