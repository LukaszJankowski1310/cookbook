package com.example.cookbook.tmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RecipeDetailViewModel : ViewModel() {
    private val currentStep = MutableLiveData<Int>(0)

    val data = listOf<String>("ala", "ma", "kota")

    fun getCurrentStep() : LiveData<Int> {
        return currentStep
    }

    fun moveToNextStep() {
        val currentValue = currentStep.value
        if (currentValue != null && currentValue < data.size-1) {
            currentStep.value = currentValue + 1
        }
    }

    fun moveToPreviousStep () {
        val currentValue = currentStep.value
        if (currentValue != null && currentValue > 0) {
            currentStep.value = currentValue - 1
        }
    }

    public fun getData() : String? {
        return currentStep.value?.let { data[it] }
    }


}