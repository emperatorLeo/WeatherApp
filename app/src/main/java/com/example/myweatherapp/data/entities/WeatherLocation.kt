package com.example.myweatherapp.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherLocation(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    val url: String
)