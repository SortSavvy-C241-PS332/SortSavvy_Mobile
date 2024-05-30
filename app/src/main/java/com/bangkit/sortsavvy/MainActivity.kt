package com.bangkit.sortsavvy

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bangkit.sortsavvy.onboarding.ui.OnboardingActivity
import com.bangkit.sortsavvy.onboarding.ui.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // splash screen harus sebelum setContentView halaman activity_main
        Thread.sleep(2000)
        installSplashScreen()

        firstTimeInstalledApp()
    }

    private fun firstTimeInstalledApp() {
        sharedPreferences = getSharedPreferences("SortSavvy", MODE_PRIVATE)
        val alreadySawOnboarding = sharedPreferences.getBoolean("already_saw_onboarding", false)

        if (alreadySawOnboarding) {
            moveToWelcomeActivity()
        } else {
            moveToOnboardingActivity()
        }
    }

    private fun moveToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToOnboardingActivity() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }
}