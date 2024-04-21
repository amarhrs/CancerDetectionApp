package com.dicoding.asclepius.view.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.di.Injection
import com.dicoding.asclepius.repository.ArticleRepository

class ArticleViewModelFactory private constructor(private val articleRepository: ArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ArticleViewModelFactory? = null

        fun getInstance(): ArticleViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ArticleViewModelFactory(Injection.provideRepository())
            }.also { INSTANCE = it }
    }
}