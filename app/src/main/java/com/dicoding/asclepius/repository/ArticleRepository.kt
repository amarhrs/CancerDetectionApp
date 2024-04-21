package com.dicoding.asclepius.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.data.remote.retrofit.ApiService
import com.dicoding.asclepius.data.Result

class ArticleRepository(
    private val apiService: ApiService
) {
    fun getListArticle(): LiveData<Result<List<ArticlesItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticle("cancer", "health", "en", BuildConfig.API_KEY)
            if (response.status == "ok") {
                emit(Result.Success(response.articles?.filterNotNull() ?: emptyList()))
            } else {
                emit(Result.Error("Failed to get data"))
            }
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getListArticle:  ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }

    }
}