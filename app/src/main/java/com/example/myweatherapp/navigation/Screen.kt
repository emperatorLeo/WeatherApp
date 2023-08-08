package com.example.myweatherapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("Splash")
    object Search : Screen("Search")
    object Detail : Screen("Detail")
}