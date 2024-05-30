package com.bangkit.sortsavvy.authentication.register.ui

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.sortsavvy.authentication.login.ui.LoginActivity
import com.bangkit.sortsavvy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()

        binding.loginNowTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setUnderLineGuestModeTextView() {
        val spannableString = SpannableString(binding.loginNowTextView.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, binding.loginNowTextView.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginNowTextView.text = spannableString
    }
}