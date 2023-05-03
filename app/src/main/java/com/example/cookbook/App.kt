package com.example.cookbook

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class App : Application(), ViewModelStoreOwner {
    private val viewModelStore = ViewModelStore()
    override fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }


}