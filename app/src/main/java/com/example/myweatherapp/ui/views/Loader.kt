package com.example.myweatherapp.ui.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loader(modifier: Modifier) {
    CircularProgressIndicator(
        modifier.size(100.dp),
        color = Color.White,
        strokeWidth = 5.dp
    )
}