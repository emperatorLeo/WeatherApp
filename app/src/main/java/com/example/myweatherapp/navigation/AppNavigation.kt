package com.example.myweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myweatherapp.ui.screens.DetailScreen
import com.example.myweatherapp.ui.screens.SearchBarScreen
import com.example.myweatherapp.ui.screens.SplashScreen
import com.example.myweatherapp.ui.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Search.route) {
            SearchBarScreen(viewModel = viewModel, navController)
        }

        composable(Screen.Detail.route) {
            DetailScreen(viewModel = viewModel)
        }
    }
}