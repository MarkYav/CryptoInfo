package com.example.cryptoinfo.common

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Failure<out T : Any>(val data: T?, val message: String, val reason: Exception) : Resource<T>()
    data class Loading<out T : Any>(val data: T?) : Resource<T>()
}