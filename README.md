# 🌦️ WeatherApp - Modern Android MVVM Architecture

A clean, modern, and production-ready Android application that displays real-time weather information and a 7-day forecast using the **OpenWeatherMap API**. Built with Kotlin and following modern Android development practices, this project showcases a robust implementation of the **MVVM (Model-View-ViewModel)** architectural pattern.

---

## 🚀 Key Features
- **Real-time Weather Data:** Fetches live temperature, humidity, wind speed, and weather descriptions.
- **Dynamic Themes:** Automatically switches the app UI background and weather icons (e.g., Sunny/Cloudy/Rainy) based on current weather conditions.
- **7-Day Forecast:** Displays a clean forecast list using a dynamic `RecyclerView`.
- **Robust Error Handling:** Professional status code handling (401, 404, etc.) and explicit network connectivity checks via a generic sealed class state management system.

---

## 🛠️ Tech Stack & Dependencies

This project leverages the official Android Jetpack libraries and modern networking tools:

*   **Language:** Kotlin 
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **Networking:** [Retrofit2](https://github.com/square/retrofit) & [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) (Type-safe HTTP client & JSON parser)
*   **HTTP Logging:** [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) (For inspecting network requests in Logcat)
*   **Asynchronous Processing:** Kotlin Coroutines (Non-blocking network requests)
*   **Jetpack Components:** 
    *   `ViewModel` (Retains UI data through configuration changes)
    *   `LiveData` (Lifecycle-aware data observation)
    *   `View Binding` (Null-safe UI component interaction)

---

## 📂 Project Folder Structure

The code-base follows a strict **layer-by-feature / layer-by-type** separation to ensure clean boundaries, scalability, and ease of testing.

```text
com.example.weatherapp/
│
├── data/                         # Data Layer (Network, Models, Repositories)
│   ├── model/                    # Pure Kotlin Data Classes mapped from JSON Response
│   │   ├── Clouds.kt
│   │   ├── Coord.kt
│   │   ├── Main.kt
│   │   ├── Sys.kt
│   │   ├── Weather.kt
│   │   ├── WeatherResponse.kt    # Root API response model
│   │   └── Wind.kt
│   │
│   ├── remote/                   # Network Configuration Components
│   │   ├── WeatherApiService.kt  # Retrofit Endpoint definitions interface
│   │   └── RetrofitClient.kt     # Singleton OkHttpClient & Retrofit provider
│   │
│   └── repository/               # Single Source of Truth for Data Operations
│       └── WeatherRepository.kt  # Handles API logic and maps responses to States
│
├── ui/                           # UI Layer (Activities, ViewModels, Adapters)
│   ├── adapter/
│   │   └── ForecastAdapter.kt    # RecyclerView Adapter using View Binding for 7-day list
│   │
│   └── weather/
│       ├── WeatherActivity.kt    # Renders UI, handles clicks, and observes ViewModel
│       └── WeatherViewModel.kt   # Communicates with Repository using Coroutines & updates LiveData
│
└── utils/                        # Shared Helpers & Utility Components
    ├── Constants.kt              # App-wide fixed configurations (BASE_URL, API_KEY, Units)
    └── Resource.kt               # Generic Sealed Class for managing API States (Loading, Success, Error)
