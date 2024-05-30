package com.bangkit.sortsavvy.onboarding.ui

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.sortsavvy.MainActivity
import com.bangkit.sortsavvy.authentication.login.ui.LoginActivity
import com.bangkit.sortsavvy.authentication.register.ui.RegisterActivity
import com.bangkit.sortsavvy.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.guestModeTextView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setUnderLineGuestModeTextView() {
        val spannableString = SpannableString(binding.guestModeTextView.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, binding.guestModeTextView.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.guestModeTextView.text = spannableString
    }
}