package com.bangkit.sortsavvy.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.pref.OnboardingPreferences
import com.bangkit.sortsavvy.data.pref.onboardingDataStore
import com.bangkit.sortsavvy.databinding.ActivityMainBinding
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val getOnboardingPreferences = OnboardingPreferences
            .getInstance(this.onboardingDataStore)
            .getOnboardingViewedStatus()

        println(getOnboardingPreferences)
        ViewComponentUtil.showToast(this, "onboarding status: $getOnboardingPreferences")

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_explore,
                R.id.navigation_snap,
                R.id.navigation_challenge,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}