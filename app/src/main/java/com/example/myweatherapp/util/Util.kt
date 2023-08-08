package com.example.myweatherapp.util

import com.example.myweatherapp.data.entities.ForecastResponse
import com.example.myweatherapp.data.entities.LocationResponse
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.model.ForecastDay
import com.example.myweatherapp.model.ForecastItem
import com.example.myweatherapp.model.Location

const val API_KEY = "ENTER YOUR API KEY HERE"

fun LocationResponse.mapToLocation(): Location = Location(name, country, latitude, longitude)

fun ForecastResponse.mapToForecast(): Forecast {
    val list = mutableListOf<ForecastItem>()

    val todayItem = ForecastItem(
        ForecastDay.TODAY,
        currentWeather.condition.weatherText,
        "https:${currentWeather.condition.weatherIcon}",
        currentWeather.temperature
    )
    var tomorrowItem: ForecastItem
    var afterTomorrow: ForecastItem

    val getTomorrow = getDay(location.localTime, 1)
    val getAfterTomorrow = getDay(location.localTime, 2)

    forecast.forecastDays.first {
        it.date == getTomorrow
    }.let {
        tomorrowItem = ForecastItem(
            ForecastDay.TOMORROW,
            it.day.condition.weatherText,
            "https:${it.day.condition.weatherIcon}",
            it.day.averageTemperature
        )
    }

    forecast.forecastDays.first {
        it.date == getAfterTomorrow
    }.let {
        afterTomorrow = ForecastItem(
            ForecastDay.DAYAFTERTOMORROW,
            it.day.condition.weatherText,
            "https:${it.day.condition.weatherIcon}",
            it.day.averageTemperature
        )
    }

    list.addAll(listOf(todayItem, tomorrowItem, afterTomorrow))
    return Forecast(place = location.name, list)
}

private fun getDay(currentDate: String, plusDay: Int): String {
    val getDate = currentDate.subSequence(0, 10).toString()
    val day = getDate.subSequence(8, 10).toString().toInt() + plusDay

    return if (day < 10) {
        getDate.replaceRange(8, 10, "0$day")
    } else {
        getDate.replaceRange(8, 10, day.toString())
    }
}