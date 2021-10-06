package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var city:String=""
    val API="5495db5ebe5be6fc6b50eb866e19777d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btError.setOnClickListener {
            city = "10035"
            requestAPI()
        }
        btZip.setOnClickListener {

            city=etZip.text.toString()

            requestAPI()
            etZip.text.clear()
            //hide keyboard
            val imm = ContextCompat.getSystemService(this, InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
            //hide RelativeLayout
            rlZip.isVisible = false
        }
        requestAPI()
    }

    private fun requestAPI(){
        println("CITY: $city")
        CoroutineScope(IO).launch {
            updateStatus(-1)                     // -1 = loading
            val data = async {
                fetchWeatherData()
            }.await()
            if(data.isNotEmpty()){
                updateWeatherData(data)
                updateStatus(0)                 // 0 = loaded
            }else{
                updateStatus(1 )                //1 = error
            }
        }
    }
    private fun fetchWeatherData(): String{
        var response = ""
        try {
            response = URL("https://api.openweathermap.org/data/2.5/weather?zip=$city&units=metric&appid=$API")
                .readText(Charsets.UTF_8)
            //test
            Log.d("response123123",response)
        }catch (e: Exception){
            println("Error: $e")
        }
        return response
    }

    private suspend fun updateWeatherData(result: String){
        withContext(Dispatchers.Main){
            val jsonObj = JSONObject(result)
            val main = jsonObj.getJSONObject("main") // for temp_min & temp_max & pressure & humidity
            val sys = jsonObj.getJSONObject("sys") // for sunrise & sunset
            val wind = jsonObj.getJSONObject("wind") // for speed
            val weather = jsonObj.getJSONArray("weather").getJSONObject(0) // for description

            val lastUpdate:Long = jsonObj.getLong("dt")
            val lastUpdateText = "Updated at: " + SimpleDateFormat(
                "dd/MM/yyyy hh:mm a",//                                  what is a         ****
                Locale.ENGLISH).format(Date(lastUpdate*1000)) //           need to explain   ****
            val currentTemperature = main.getString("temp")
            val temp = try{                                         // why hear we use try  but in temp min and max we don't use it
                currentTemperature.substring(0, currentTemperature.indexOf(".")) + "°C"
            }catch(e: Exception){
                currentTemperature + "°C"
            }
            val minTemperature = main.getString("temp_min")
            val tempMin = "Low: " + minTemperature.substring(0, minTemperature.indexOf("."))+"°C"
            val maxTemperature = main.getString("temp_max")
            val tempMax = "High: " + maxTemperature.substring(0, maxTemperature.indexOf("."))+"°C"
            val pressure = main.getString("pressure")
            val humidity = main.getString("humidity")

            val sunrise:Long = sys.getLong("sunrise")
            val sunset:Long = sys.getLong("sunset")
            val windSpeed = wind.getString("speed")
            val weatherDescription = weather.getString("description")

            val address = jsonObj.getString("name")+", "+sys.getString("country")

            tvAddress.text = address
            tvAddress.setOnClickListener {
                rlZip.isVisible = true
            }
            updated_at.text =  lastUpdateText
            tvStatus.text = weatherDescription.capitalize(Locale.getDefault())
            tvTemp.text = temp
            tvTempMin.text = tempMin
            tvTempMax.text = tempMax
            tvSunrise.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
            tvSunset.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
            tvWind.text = windSpeed
            tvPressure.text = pressure
            tvHumidity.text = humidity
            llRefresh.setOnClickListener { requestAPI() }
        }
    }


    private suspend fun updateStatus(state: Int){
//        states: -1 = loading, 0 = loaded, 1 = error
        withContext(Dispatchers.Main){
            when{
                state < 0 -> {  //loading
                    loader.visibility = View.VISIBLE
                    mainContainer.visibility = View.GONE
                    llErrorContainer.visibility = View.GONE


                }
                state == 0 -> {  //loaded
                    loader.visibility = View.GONE
                    llErrorContainer.visibility = View.GONE
                    mainContainer.visibility = View.VISIBLE


                }
                state >0 -> {       //error
                    mainContainer.visibility = View.GONE
                    loader.visibility = View.GONE
                    llErrorContainer.visibility = View.VISIBLE
                }
            }
        }
    }



}