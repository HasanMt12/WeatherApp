package com.example.weatherapp.ui.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityWeatherBinding
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.data.model.WeatherResponse
import java.util.Locale

class WeatherActivity : AppCompatActivity() {

    // View Binding & ViewModel Setup
    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default City Weather
        viewModel.getWeather("Dhaka")

        // Search Button Logic
        binding.btnSearch.setOnClickListener {
            val city = binding.etSearchCity.text.toString().trim()
            if (city.isNotEmpty()) {
                viewModel.getWeather(city)
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }

        // ViewModel (Observe)
        initObservers()
    }

    private fun initObservers() {
        viewModel.weatherData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading Show
                    binding.loadingView.root.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // Data show and loading Hide
                    binding.loadingView.root.visibility = View.GONE
                    resource.data?.let { updateUI(it) }
                }
                is Resource.Error -> {
                    // Error Message Show and Loading Hide
                    binding.loadingView.root.visibility = View.GONE
                    Toast.makeText(this, resource.message ?: "An error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateUI(weather: WeatherResponse) {
        // XML View Data Set
        binding.tvCityName.text = weather.name
        binding.tvTemperature.text = "${weather.main.temp.toInt()}°C"
        binding.tvHumidity.text = "${weather.main.humidity}%"
        binding.tvWindSpeed.text = "${weather.wind.speed} m/s"

        // Weather Description (Capitalize)
        val description = weather.weather.firstOrNull()?.description ?: "Clear Sky"
        binding.tvWeatherDescription.text = description.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

        // Dynamic Icon
        val mainCondition = weather.weather.firstOrNull()?.main ?: "Clear"
        updateWeatherTheme(mainCondition)
    }

    private fun updateWeatherTheme(condition: String) {
        when (condition.lowercase(Locale.ROOT)) {
            "clear" -> {
                binding.mainLayout.setBackgroundResource(R.drawable.bg_sunny)
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_sunny)
            }
            "clouds" -> {
                binding.mainLayout.setBackgroundResource(R.drawable.bg_night)
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_cloudy)
            }
            "rain", "drizzle", "thunderstorm" -> {
                binding.mainLayout.setBackgroundResource(R.drawable.bg_night)
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_rainy)
            }
            else -> {
                binding.mainLayout.setBackgroundResource(R.drawable.bg_sunny)
                binding.ivWeatherIcon.setImageResource(R.drawable.ic_sunny)
            }
        }
    }
}