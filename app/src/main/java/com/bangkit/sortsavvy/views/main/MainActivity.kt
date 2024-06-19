package com.bangkit.sortsavvy.views.main

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.pref.OnboardingPreferences
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.pref.onboardingDataStore
import com.bangkit.sortsavvy.data.pref.userDataStore
import com.bangkit.sortsavvy.data.remote.response.UserDataLogin
import com.bangkit.sortsavvy.databinding.ActivityMainBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.authentication.login.LoginViewModel
import com.bangkit.sortsavvy.views.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

//    private lateinit var userData: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val viewModelFactory= ViewModelFactory.getInstance(this@MainActivity)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

//        val getOnboardingPreferences = OnboardingPreferences
//            .getInstance(this.onboardingDataStore)
//            .getOnboardingViewedStatus()
//
//        println("onboarding viewed? $getOnboardingPreferences")

//        lifecycleScope.launch {
//            getOnboardingPreferences.collect { isOnboardingViewed ->
//                println("onboarding viewed? $isOnboardingViewed")
//            }
//        }

//        val getUserSessionPreferences = UserPreferences
//            .getInstance(this.userDataStore)
//            .getSession()
//
//        lifecycleScope.launch {
//            getUserSessionPreferences.collect { userModel ->
//                println("User ID: ${userModel?.userId}")
//                println("Email: ${userModel?.email}")
//                println("Full Name: ${userModel?.fullName}")
//                println("Profile Photo: ${userModel?.profilePhoto}")
//                println("Is Login: ${userModel?.isLogin}")
//            }
//        }

        viewModel.getSession().observe(this) { userModel ->
            if (!userModel.isLogin && userModel.isOnboardingViewed) {
                navigateToWelcomeActivity()
            }
        }

        setupBottomNavigation()
    }

    private fun navigateToWelcomeActivity() {
        val moveIntent = Intent(this@MainActivity, WelcomeActivity::class.java)
        moveIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(moveIntent)
//        finish()
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home,
//                R.id.navigation_explore,
//                R.id.navigation_snap,
//                R.id.navigation_challenge,
//                R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // custom bottom navigation for specific fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.hide()
            when (destination.id) {
                R.id.navigation_snap -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_snap_result -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_profile_settings -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_profile_settings_change_password -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_challenge -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_challenge_result -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_explore_detail_snap -> {
                    navView.visibility = View.GONE
                }
                R.id.navigation_explore_detail_cara_olah -> {
                    navView.visibility = View.GONE
                }
                else -> {
                    navView.visibility = View.VISIBLE
                }
            }
        }
    }
}