package com.example.myweatherapp

import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.model.ForecastDay
import com.example.myweatherapp.model.ForecastItem
import com.example.myweatherapp.model.Location

object MockResponse {
    fun provideLocationResponse() = listOf(
        Location("Location1", "country1", 1.0, 1.0),
        Location("Location2", "country2", 2.0, 2.0),
        Location("Location3", "country3", 3.0, 3.0)
    )

    fun provideLocationEmptyResponse() = listOf<Location>()

    fun provideForecastResponse() =
        Forecast(
            "Place",
            listOf(
                ForecastItem(ForecastDay.TODAY, "weatherText1", "icon1", 1.1),
                ForecastItem(ForecastDay.TODAY, "weatherText2", "icon2", 2.2),
                ForecastItem(ForecastDay.TODAY, "weatherText3", "icon3", 3.3)
            )
        )
}