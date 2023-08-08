package com.example.myweatherapp.di

import android.content.Context
import com.example.myweatherapp.ConnectionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext appContext: Context): ConnectionManager {
        return ConnectionManager(appContext)
    }
}