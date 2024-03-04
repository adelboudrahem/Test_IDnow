package com.example.idnow_v2application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProductApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}