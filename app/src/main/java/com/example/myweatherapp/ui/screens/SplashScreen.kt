package com.example.myweatherapp.ui.screens

import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myweatherapp.R
import com.example.myweatherapp.ui.MainActivity

@Composable
fun SplashScreen(activity: MainActivity) {
    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    } else {
        activity.window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    Image(
        bitmap = ImageBitmap.imageResource(id = R.drawable.forecast_logo),
        contentDescription = "Splash screen Icon",
        Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(MainActivity())
}