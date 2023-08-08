package com.example.myweatherapp.model

data class Forecast(val place: String, val list: List<ForecastItem>)
data class ForecastItem(
    val forecastDay: ForecastDay,
    val weatherText: String,
    val weatherIcon: String,
    val averageTemp: Double
)

enum class ForecastDay(val dayName: String) {
    TODAY("Today"), TOMORROW("Tomorrow"),
    DAYAFTERTOMORROW("Day after tomorrow")
}