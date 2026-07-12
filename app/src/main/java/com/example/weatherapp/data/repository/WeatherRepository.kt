package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.RetrofitClient
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.Resource
import java.io.IOException

class WeatherRepository {

    suspend fun fetchWeather(cityName: String): Resource<WeatherResponse> {
        return try {
            val response = RetrofitClient.apiService.getWeatherData(
                cityName = cityName,
                apiKey = Constants.API_KEY,
                units = Constants.METRIC_UNIT
            )

            // Resource State and status handle
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                // Custom Error
                when (response.code()) {
                    404 -> Resource.Error("City not found! Please check the name.")
                    401 -> Resource.Error("Invalid API Key! Please check your Constants file.")
                    else -> Resource.Error("Something went wrong: ${response.message()}")
                }
            }
        } catch (e: IOException) {
            // Network Drop Error
            Resource.Error("No internet connection. Please check your network.")
        } catch (e: Exception) {
            // Others Error
            Resource.Error("An unexpected error occurred: ${e.localizedMessage}")
        }
    }
}