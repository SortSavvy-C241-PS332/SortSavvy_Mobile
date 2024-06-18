package com.bangkit.sortsavvy.views.main

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class SortSavvy : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}