package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class HistoryViewModelFactory private constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: HistoryViewModelFactory? = null

        fun getInstance(application: Application): HistoryViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HistoryViewModelFactory(application)
            }.also { INSTANCE = it }
    }
}