package com.example.myweatherapp.ui

sealed class UiState {
    object Loading : UiState()

    object Success : UiState()

    sealed class Error : UiState() {
        object ServerError : Error()
        object BadRequestError : Error()
        object ConnectionError : Error()
    }
}