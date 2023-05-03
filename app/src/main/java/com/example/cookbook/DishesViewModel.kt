package com.example.cookbook

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookbook.api.DataRepository
import com.example.cookbook.api.model.*

class DishesViewModel : ViewModel() {
    private val repository : DataRepository = DataRepository()
    private val data : MutableLiveData<RecipesList> = repository.cookHttpRequest() as MutableLiveData<RecipesList>

    private val dish : MutableLiveData<Result> = MutableLiveData()

    private val dishSteps : MutableLiveData<List<Step>> = MutableLiveData(emptyList())
    private val currentStep : MutableLiveData<Int> = MutableLiveData(0)

    fun getData() : LiveData<RecipesList> {
        return data
    }
    fun getDish() : LiveData<Result> {
        return dish
    }

    fun getDishSteps() : LiveData<List<Step>> {
        return dishSteps
    }
    fun setDish(result: Result) {
        this.dish.value = result
        this.dishSteps.value = result.analyzedInstructions[0].steps
        this.currentStep.value = 0
    }

    fun getCurrentStep() : LiveData<Int> {
        return currentStep
    }

    fun moveToNextStep() {
        val currentValue = currentStep.value
        if (currentValue != null) {
            if (currentValue < dishSteps.value!!.size -1 ) {
                currentStep.value = currentValue + 1
            }
        }
    }
    fun moveToPreviousStep () {
        val currentValue = currentStep.value
        if (currentValue != null && currentValue > 0) {
            currentStep.value = currentValue - 1
        }
    }



}