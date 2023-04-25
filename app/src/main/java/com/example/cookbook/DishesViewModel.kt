package com.example.cookbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookbook.api.DataRepository
import com.example.cookbook.api.model.RecipesList

class DishesViewModel : ViewModel() {
    private val repository : DataRepository = DataRepository()
    private val data : MutableLiveData<RecipesList> = repository.cookHttpRequest() as MutableLiveData<RecipesList>

    fun getData() : LiveData<RecipesList> {
        return data
    }

}