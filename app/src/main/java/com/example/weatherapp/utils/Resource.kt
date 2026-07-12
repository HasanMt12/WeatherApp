package com.example.weatherapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    // Data Load
    class Loading<T> : Resource<T>()

    // Data Success
    class Success<T>(data: T) : Resource<T>(data = data)

    // Data Error
    class Error<T>(message: String, data: T? = null) : Resource<T>(data = data, message = message)
}