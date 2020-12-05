package com.antroid.news.api

import android.util.Log
import com.antroid.news.database.NewsDataTable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    private const val TAG = "SERVICE"
    private const val HOST = "https://www.cbc.ca/aggregate_api/v1/"
    private val api: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create<Api>(Api::class.java)
    }

    fun getNewsFeed(callback: (result: Outcome<List<NewsDataTable>>) -> Unit){
        val response = api.getNews().execute()
        if (response.isSuccessful){
            callback.invoke(Outcome.Success(response.body()))
        } else {
            Log.e(response.message(), TAG)
        }
    }

}