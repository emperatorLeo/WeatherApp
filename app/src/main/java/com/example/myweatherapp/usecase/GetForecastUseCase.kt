package com.example.myweatherapp.usecase

import android.util.Log
import com.example.myweatherapp.data.WeatherRepository
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.util.mapToForecast
import javax.inject.Inject
import retrofit2.Response

class GetForecastUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend operator fun invoke(latitude: Double, longitude: Double): Response<Forecast> {
        val response = repository.getForecast(latitude, longitude)

        return if (response.isSuccessful) {
            Log.d("Leo UseCase", "Body: ${response.body()}")
            val responseMapped = response.body()?.mapToForecast()
            Response.success(responseMapped)
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }
}