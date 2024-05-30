package com.bangkit.sortsavvy

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.bangkit.sortsavvy.collection.ui.CollectionFragment
import com.bangkit.sortsavvy.databinding.ActivityMainBinding
import com.bangkit.sortsavvy.explore.ui.ExploreFragment
import com.bangkit.sortsavvy.home.ui.HomeFragment
import com.bangkit.sortsavvy.onboarding.ui.OnboardingActivity
import com.bangkit.sortsavvy.onboarding.ui.WelcomeActivity
import com.bangkit.sortsavvy.profile.ui.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // splash screen harus sebelum setContentView halaman activity_main
        Thread.sleep(2000)
        installSplashScreen()

        if (isFirstTimeInstalledApp()) {
            moveToOnboardingActivity()
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }

        setDisplayFragment()
        binding.bottomNavView.background = null  // karena transparant background, jadiin null

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.beranda -> replaceFragment(HomeFragment())
                R.id.petualangan -> replaceFragment(ExploreFragment())
                R.id.koleksi -> replaceFragment(CollectionFragment())
                R.id.profil -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun setDisplayFragment() {
        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun isFirstTimeInstalledApp(): Boolean {
        sharedPreferences = getSharedPreferences("SortSavvy", MODE_PRIVATE)
        val isFirstTimeLaunch = sharedPreferences.getBoolean("is_first_time_launch", true)

        return isFirstTimeLaunch

//        if (alreadySawOnboarding) {
//            moveToWelcomeActivity()
//        } else {
//            moveToOnboardingActivity()
//        }
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