package com.rhea.epoxy.api

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failed<T>(val exception: Exception) : Resource<T>()

    companion object{
        fun <T> success(data: T) = Success(data)
        fun <T> failed(e: Exception) = Failed<T>(e)
    }
}