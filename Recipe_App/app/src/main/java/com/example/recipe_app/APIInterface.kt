package com.example.recipe_app

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {


        @GET("/recipes/")
    fun getRecipies(): Call<List<books.UserDetails>>

        @POST("/recipes/")
        fun addUser(@Body userData: books.UserDetails): Call<books>


    }