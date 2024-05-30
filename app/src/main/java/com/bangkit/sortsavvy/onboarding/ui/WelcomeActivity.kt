package com.bangkit.sortsavvy.onboarding.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.sortsavvy.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()
    }

    private fun setUnderLineGuestModeTextView() {
        val spannableString = SpannableString(binding.guestModeTextView.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, binding.guestModeTextView.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.guestModeTextView.text = spannableString
    }
}