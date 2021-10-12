package com.example.headsup

import retrofit2.Call
import retrofit2.http.*
interface APIInterface {
    @GET("/celebrities/")
    fun getCelebrities(): Call<ArrayList<Celebrity>>


}