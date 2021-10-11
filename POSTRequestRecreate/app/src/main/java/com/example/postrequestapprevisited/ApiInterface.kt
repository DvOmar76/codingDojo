package com.example.postrequestapprevisited

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("/test/")
    fun getUser():Call<Array<DataItem>>
    @POST("/test/")
    fun addUserToApi(@Body userData:DataItem):Call<DataItem>

}