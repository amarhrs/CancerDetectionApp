package com.dicoding.asclepius.view.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.repository.ArticleRepository

class ArticleViewModel(articleRepository: ArticleRepository) : ViewModel() {
    val listArticle: LiveData<Result<List<ArticlesItem>>> = articleRepository.getListArticle()
}