package com.example.myweatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myweatherapp.data.network.WeatherApiClient
import com.example.myweatherapp.routing.Screen
import com.example.myweatherapp.ui.screens.SplashScreen
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var weatherApiClient: WeatherApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController,
                    startDestination = Screen.Splash.route) {

                    composable(Screen.Splash.route) {
                        SplashScreen(this@MainActivity)
                    }

                    /* TODO hacer el SearchScreen y agregarlo al grafo*/
                }
            }
        }
    }
}