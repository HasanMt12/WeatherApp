package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherResponse>
}