package com.dicoding.asclepius.view.result

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.local.entity.History
import com.dicoding.asclepius.repository.HistoryRepository

class ResultViewModel(application: Application): ViewModel() {
    private val historyRepository: HistoryRepository = HistoryRepository(application)

    private fun insert(history: History) = historyRepository.insert(history)

    fun saveHistory(image: String, prediction: String, score: String) {
        val history = History(image = image, prediction = prediction, score = score)
        insert(history)
    }
}