package com.dicoding.asclepius.view.result

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory private constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ResultViewModelFactory? = null

        fun getInstance(application: Application): ResultViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ResultViewModelFactory(application)
            }.also { INSTANCE = it }
    }
}