package com.example.headsup

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Surface
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.rotate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class Game : AppCompatActivity() {
    private val apiInterface by lazy { APIClient().getClient().create(APIInterface::class.java) }
    private  var list=ArrayList<Celebrity>()
    private  var gameActive:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpageingame)
        newTimer()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.rotate)
            fetchData()
            gameActive=true
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.firstpageingame)
        }
    }

    fun fetchData(){

            apiInterface.getCelebrities().enqueue(object: Callback<ArrayList<Celebrity>> {
                override fun onResponse(
                    call: Call<ArrayList<Celebrity>>,
                    response: Response<ArrayList<Celebrity>>
                ) {
//                    var count=0
                    for(ob in response.body()!!) {
                      var obCelebrity=Celebrity(ob.name,ob.taboo1,ob.taboo2,ob.taboo3)
                        list.add(obCelebrity)
//                        count++
                    }
                    var randomIndex= Random.nextInt(list.size-1)

                    tvShow.setText("${list[randomIndex].name}")
                   var text="${list[randomIndex].taboo1}\n${list[randomIndex].taboo2}\n${list[randomIndex].taboo3}\n"
                    tvTaboo.setText(text)
                }

                override fun onFailure(call: Call<ArrayList<Celebrity>>, t: Throwable) {
                    Toast.makeText(this@Game, "Unable to get data", Toast.LENGTH_LONG).show()
                }
            })
        }

    private fun newTimer(){
        if(!gameActive){
            gameActive = true
            object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTime.text = "Time: ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    gameActive = false
                    intent=Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }
            }.start()
        }
    }


}