package com.example.myweatherapp.ui.states

sealed class UiState {
    object Loading : UiState()

    object Success : UiState()

    sealed class Error : UiState() {
        object ServerError : Error()
        object BadRequestError : Error()
        object ConnectionError : Error()
        object EmptySearch : Error()
        object TimeOut : Error()
        object UnknownError : Error()
        object EmptyResultError : Error()
    }
}