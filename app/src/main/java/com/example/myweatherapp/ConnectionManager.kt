package com.example.myweatherapp

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class ConnectionManager @Inject constructor(private val context: Context) {
    @Suppress("DEPRECATION")
    operator fun invoke(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo?.isConnected ?: false
    }
}