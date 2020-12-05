package com.antroid.news.api

sealed class Outcome<out T> {
    data class Success<T>(val response: T?) : Outcome<T>()
    data class Failure<T>(val errorResponse: String): Outcome<Nothing>()
}