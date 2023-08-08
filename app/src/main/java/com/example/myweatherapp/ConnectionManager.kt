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

    /* override fun observer(): Flow<ConnectivityObserver.Status> {
         return callbackFlow {
             val callback = object : ConnectivityManager.NetworkCallback() {
                 override fun onAvailable(network: Network) {
                     super.onAvailable(network)
                     launch {
                         send(ConnectivityObserver.Status.Available)
                     }
                 }

                 override fun onUnavailable() {
                     super.onUnavailable()
                     launch {
                         send(ConnectivityObserver.Status.Unavailable)
                     }
                 }
             }
             connectivityManager.registerDefaultNetworkCallback(callback)
             awaitClose {
                 connectivityManager.unregisterNetworkCallback(callback)
             }
         }
     }*/
}