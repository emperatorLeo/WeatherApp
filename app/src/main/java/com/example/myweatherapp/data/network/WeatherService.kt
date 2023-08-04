package com.example.myweatherapp.data.network

import com.example.myweatherapp.data.entities.WeatherLocation
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherService @Inject constructor(private val api: WeatherApiClient) {

    suspend fun getLocation(location: String): Response<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            api.getLocation(location = location)
        }
    }
}
