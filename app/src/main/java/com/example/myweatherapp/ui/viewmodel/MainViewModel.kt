package com.example.myweatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.model.Location
import com.example.myweatherapp.usecase.SearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val searchLocationUseCase: SearchLocationUseCase) :
    ViewModel() {

    private val _locationList = MutableLiveData<List<Location>>(listOf())
    val locationList: LiveData<List<Location>> = _locationList

    fun searchLocation(location: String) {
        viewModelScope.launch {
            val response = searchLocationUseCase(location)

            if (response.isSuccessful) {
                Log.d("Leo", "response: ${response.body()}")
                _locationList.value = response.body()
            } else {
                Log.d("Leo", "Error: ${response.message()}")
            }
        }
    }
}