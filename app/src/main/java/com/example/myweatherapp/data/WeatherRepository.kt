package com.example.myweatherapp.data

import com.example.myweatherapp.data.network.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val service: WeatherService) {
    suspend fun getLocation(location: String) = service.getLocation(location)

    suspend fun getForecast(latitude: Double, longitude: Double) =
        service.getForecast(latitude, longitude)
}