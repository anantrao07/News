package com.antroid.news.api

import com.antroid.news.database.NewsDataTable
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("items?lineupSlug=news")
    fun getNews(): Call<List<NewsDataTable>>

}