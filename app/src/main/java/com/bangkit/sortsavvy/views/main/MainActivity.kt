package com.bangkit.sortsavvy.views.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.pref.OnboardingPreferences
import com.bangkit.sortsavvy.data.pref.onboardingDataStore
import com.bangkit.sortsavvy.databinding.ActivityMainBinding
import com.bangkit.sortsavvy.di.OnboardingInjection
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getOnboardingPreferences = OnboardingPreferences
            .getInstance(this.onboardingDataStore)
            .getOnboardingViewedStatus()

        println(getOnboardingPreferences)
        ViewComponentUtil.showToast(this, "onboarding status: $getOnboardingPreferences")

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}