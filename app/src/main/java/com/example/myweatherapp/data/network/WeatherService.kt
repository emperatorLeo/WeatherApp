package com.example.myweatherapp.data.network

import com.example.myweatherapp.data.entities.ForecastResponse
import com.example.myweatherapp.data.entities.LocationResponse
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherService @Inject constructor(private val api: WeatherApiClient) {

    suspend fun getLocation(location: String): Response<List<LocationResponse>> {
        return withContext(Dispatchers.IO) {
            api.getLocation(location = location)
        }
    }

    suspend fun getForecast(latitude: Double, longitude: Double): Response<ForecastResponse> {
        return withContext(Dispatchers.IO) {
            api.getForecast(coordinates = "$latitude,$longitude")
        }
    }
}