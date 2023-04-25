package com.example.cookbook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val foodBaseURL = "https://api.spoonacular.com/"
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(foodBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}