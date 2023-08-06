package com.example.myweatherapp.util

import com.example.myweatherapp.data.entities.WeatherLocation
import com.example.myweatherapp.model.Location

const val API_KEY = "ENTER YOUR API KEY HERE"

fun WeatherLocation.mapToLocation(): Location = Location(name, country, latitude, longitude)