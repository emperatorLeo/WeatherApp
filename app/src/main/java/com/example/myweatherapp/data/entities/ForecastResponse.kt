package com.example.myweatherapp.data.entities

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val location: Location,
    @SerializedName("current") val currentWeather: CurrentWeather,
    val forecast: Forecast
)

data class Location(val name: String, @SerializedName("localtime") val localTime: String)

data class CurrentWeather(
    @SerializedName("temp_c") val temperature: Double,
    val condition: Condition
)

data class Condition(
    @SerializedName("text") val weatherText: String,
    @SerializedName("icon") val weatherIcon: String
)

data class Forecast(@SerializedName("forecastday") val forecastDays: List<ForecastItemResponse>)

data class ForecastItemResponse(val date: String, val day: Day)

data class Day(
    @SerializedName("avgtemp_c") val averageTemperature: Double,
    val condition: Condition
)