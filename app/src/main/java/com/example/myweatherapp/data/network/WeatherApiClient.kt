package com.example.myweatherapp.data.network

import com.example.myweatherapp.data.entities.WeatherLocation
import com.example.myweatherapp.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("/search.json")
    suspend fun getLocation(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") location: String
    ): Response<WeatherLocation>
}
