package com.example.cryptoinfo.common

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Failure(val message: String, val reason: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}