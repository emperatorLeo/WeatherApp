package com.example.myweatherapp.data.network

import com.example.myweatherapp.data.entities.ForecastResponse
import com.example.myweatherapp.data.entities.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("search.json")
    suspend fun getLocation(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") location: String
    ): Response<List<LocationResponse>>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") coordinates: String,
        @Query("days") days: Int = 3
    ): Response<ForecastResponse>
}
private const val API_KEY = "ENTER YOUR API KEY HERE"