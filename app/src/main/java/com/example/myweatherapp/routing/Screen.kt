package com.example.myweatherapp.routing

sealed class Screen(val route: String) {
    object Splash : Screen("Splash")
    object Search : Screen("Search")
}
