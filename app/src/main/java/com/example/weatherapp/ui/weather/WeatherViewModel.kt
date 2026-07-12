package com.example.weatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    // observer- UI to LiveData
    private val _weatherData = MutableLiveData<Resource<WeatherResponse>>()
    val weatherData: LiveData<Resource<WeatherResponse>> get() = _weatherData

    /**
     * Get Specific city weather Fun
     */
    fun getWeather(cityName: String) {
        // Initial loading
        _weatherData.postValue(Resource.Loading())

        // viewModelScope.launch- background thread , network call
        viewModelScope.launch {
            val result = repository.fetchWeather(cityName)
            _weatherData.postValue(result)
        }
    }
}