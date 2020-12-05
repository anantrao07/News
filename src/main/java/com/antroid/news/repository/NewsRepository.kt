package com.antroid.news.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antroid.news.api.Outcome
import com.antroid.news.api.Outcome.Success
import com.antroid.news.api.Service
import com.antroid.news.database.NewsDataTable
import com.antroid.news.database.NewsDatabase

class NewsRepository(application: Application) {

    private val database = NewsDatabase.getInstance(application)
    private val _newsListLiveData: MutableLiveData<List<NewsDataTable>> = MutableLiveData()
    val newsList:LiveData<List<NewsDataTable>>
    get() {
        return _newsListLiveData
    }

    private val _responseError: MutableLiveData<Error> = MutableLiveData()
    val responseError: LiveData<Error>
    get() {
        return _responseError
    }

    fun fetchNewsData() {
            Service.getNewsFeed { result ->
                when (result) {
                    is Success -> {
                        database?.newsDataDao()?.insertNewsFeed(result.response)
                        _newsListLiveData.postValue(result.response)
                    }
                    is Outcome.Failure<*> -> {
                        _responseError.postValue(Error())
                    }
                }
            }
        }

     fun getSavedNewsData() {
        _newsListLiveData.postValue(database?.newsDataDao()?.selectAllNewsFeed())
    }
}