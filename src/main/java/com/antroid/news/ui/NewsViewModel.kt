package com.antroid.news.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antroid.news.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): ViewModel() {
    private val repository = NewsRepository(application)
    val newsFeed = repository.newsList
    val newsFeedError = repository.responseError

    class Factory constructor(private val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModel(application) as T
        }
    }
    fun fetchNewsFeed(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchNewsData()
        }
    }

    fun fetchCachedNews(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getSavedNewsData()
        }
    }
}