package com.example.myweatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myweatherapp.routing.Screen
import com.example.myweatherapp.ui.screens.SearchBarScreen
import com.example.myweatherapp.ui.screens.SplashScreen
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import com.example.myweatherapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route
                ) {
                    composable(Screen.Splash.route) {
                        SplashScreen(this@MainActivity)
                    }

                    composable(Screen.Search.route) {
                        SearchBarScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}