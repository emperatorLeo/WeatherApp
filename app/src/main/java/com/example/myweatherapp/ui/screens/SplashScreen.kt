package com.example.myweatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavController
import com.example.myweatherapp.R
import com.example.myweatherapp.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Image(
        bitmap = ImageBitmap.imageResource(id = R.drawable.forecast_logo),
        contentDescription = "Splash screen Icon",
        Modifier
            .fillMaxSize()
            .background(Color.White)
    )

    LaunchedEffect(key1 = true) {
        delay(2500)
        navController.popBackStack()
        navController.navigate(Screen.Search.route)
    }
}