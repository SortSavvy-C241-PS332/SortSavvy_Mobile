package com.bangkit.sortsavvy.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.sortsavvy.R
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
        }
    }

    private fun setUnderLineGuestModeTextView() {
        val spannableString = SpannableString(binding.loginNowTextView.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, binding.loginNowTextView.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginNowTextView.text = spannableString
    }
}