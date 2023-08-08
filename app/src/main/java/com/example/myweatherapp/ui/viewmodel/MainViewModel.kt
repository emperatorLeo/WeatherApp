package com.example.myweatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.ConnectionManager
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.model.Location
import com.example.myweatherapp.ui.states.UiState
import com.example.myweatherapp.usecase.GetForecastUseCase
import com.example.myweatherapp.usecase.SearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchLocationUseCase: SearchLocationUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val connectionManager: ConnectionManager
) :
    ViewModel() {

    private val _locationList = MutableLiveData<List<Location>>(listOf())
    val locationList: LiveData<List<Location>> = _locationList

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private val _forecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast> = _forecast

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = UiState.Error.UnknownError
    }

    fun searchLocation(location: String) {
        when {
            (!connectionManager()) -> {
                _uiState.value = UiState.Error.ConnectionError
            }

            location.isEmpty() -> {
                _uiState.value = UiState.Error.EmptySearch
            }

            else -> {
                viewModelScope.launch(coroutineExceptionHandler) {
                    _uiState.value = UiState.Loading
                    val response = searchLocationUseCase(location)

                    if (response.isSuccessful && response.body()!!.isNotEmpty()) {
                        _uiState.value = UiState.Success
                        _locationList.value = response.body()
                    } else {
                        errorHandler(response.raw().code, response.body()!!.isEmpty())
                    }
                }
            }
        }
    }

    fun getForecast(lat: Double, long: Double) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _uiState.value = UiState.Loading
            val forecastResponse = getForecastUseCase(latitude = lat, longitude = long)
            if (forecastResponse.isSuccessful) {
                _uiState.value = UiState.Success
                _forecast.value = forecastResponse.body()
            } else {
                errorHandler(forecastResponse.raw().code)
            }
        }
    }

    private fun errorHandler(responseCode: Int, isResponseEmpty: Boolean = false) {
        if (isResponseEmpty) {
            _uiState.value = UiState.Error.EmptyResultError
        } else {
            when (responseCode) {
                in 400..499 -> {
                    _uiState.value = UiState.Error.BadRequestError
                }

                in 500..599 -> {
                    _uiState.value = UiState.Error.ServerError
                }

                else -> {
                    _uiState.value = UiState.Error.TimeOut
                }
            }
        }
    }
}