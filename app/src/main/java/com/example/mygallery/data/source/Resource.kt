package com.example.mygallery.data.source

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val errorCode: Int? = null, val message: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}