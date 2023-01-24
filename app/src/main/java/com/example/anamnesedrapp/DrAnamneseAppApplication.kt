package com.example.anamnesedrapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DrAnamneseAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}