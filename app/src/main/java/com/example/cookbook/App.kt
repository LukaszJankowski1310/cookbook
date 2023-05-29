package com.example.cookbook

import android.app.Application
import androidx.lifecycle.ViewModelProvider


class App : Application() {
    val myViewModel: DishesViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this).create(DishesViewModel::class.java)
    }


}