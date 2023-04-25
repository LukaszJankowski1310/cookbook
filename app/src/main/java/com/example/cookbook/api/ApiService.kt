package com.example.cookbook.api

import com.example.cookbook.api.model.RecipesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/recipes/complexSearch")
    fun getRecipes(@Query("apiKey") apiKey : String,
                   @Query("addRecipeInformation") addRecipeInformation : Boolean,
                   @Query("number") number: Int) : Call<RecipesList>

}