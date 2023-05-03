package com.example.cookbook.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookbook.api.model.RecipesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataRepository() {
     private val  apiService : ApiService = RetrofitInstance.api

     fun cookHttpRequest() : LiveData<RecipesList> {
         Log.i("APICALL", "APICALL")
         val data: MutableLiveData<RecipesList> = MutableLiveData<RecipesList>()
         apiService.getRecipes("0f1b4439c30e4ba2b175f44fec7dcfa6", true, 15)
            .enqueue(object : Callback<RecipesList> {
                override fun onResponse(call: Call<RecipesList>, response: Response<RecipesList>) {
                    if (response.isSuccessful) {
                        data.value = response.body();
                        Log.i("SUCCESS", data.value.toString())
                    }
                }
                override fun onFailure(call: Call<RecipesList>, t: Throwable) {
                    Log.i("FAILURE", call.request().toString())
                }
            })

         return data
    }

}