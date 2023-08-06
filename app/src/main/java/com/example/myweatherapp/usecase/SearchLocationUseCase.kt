package com.example.myweatherapp.usecase

import com.example.myweatherapp.data.WeatherRepository
import com.example.myweatherapp.model.Location
import com.example.myweatherapp.util.mapToLocation
import javax.inject.Inject
import retrofit2.Response

class SearchLocationUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(location: String): Response<List<Location>> {
        val response = repository.getLocation(location)
        return if (response.isSuccessful) {
            val responseMapped = response.body()?.map { it.mapToLocation() }
            Response.success(responseMapped)
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }
}