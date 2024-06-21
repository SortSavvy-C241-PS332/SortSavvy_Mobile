package com.bangkit.sortsavvy.views.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.sortsavvy.databinding.ActivityWelcomeBinding
import com.bangkit.sortsavvy.views.authentication.login.LoginActivity
import com.bangkit.sortsavvy.views.authentication.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
//    private lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setUnderLineGuestModeTextView()
        supportActionBar?.hide()

//        val viewModelFactory = ViewModelFactory.getInstance(this@WelcomeActivity)
//        viewModel = ViewModelProvider(this, viewModelFactory)[WelcomeViewModel::class.java]

        setButtonNavigationListener()
    }

//    private fun setUnderLineGuestModeTextView() {
//        val valueString = binding.guestModeTextView.text
//        val spannableString = ViewComponentUtil.createUnderlinedSpannableString(valueString.toString())
//
//        binding.guestModeTextView.text = spannableString
//    }

    private fun setButtonNavigationListener() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

//        binding.guestModeTextView.setOnClickListener {
//            viewModel.setOnboardingViewedStatus(true)
//            viewModel.isOnboardingViewed.observe(this) { isOnboardingViewedStatus ->
//                if (isOnboardingViewedStatus == true) {
//                    navigateToHomeScreen()
//                }
//            }
//        }
    }

//    private fun navigateToHomeScreen() {
//        val moveIntent = Intent(this@WelcomeActivity, MainActivity::class.java)
//        startActivity(moveIntent)
//        finish()
//    }
}