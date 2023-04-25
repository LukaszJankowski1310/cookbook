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
         apiService.getRecipes("691bb0541f544c4ab7888a600b242b7c", true, 15)
            .enqueue(object : Callback<RecipesList> {
                override fun onResponse(call: Call<RecipesList>, response: Response<RecipesList>) {
                    if (response.isSuccessful) {
                        data.value = response.body();
                    }
                }
                override fun onFailure(call: Call<RecipesList>, t: Throwable) {
                    Log.i("ERR", call.request().toString())
                }
            })
         return data
    }

}