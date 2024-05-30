package com.bangkit.sortsavvy.authentication.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.sortsavvy.authentication.register.ui.RegisterActivity
import com.bangkit.sortsavvy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()

        binding.buatAkunTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUnderLineGuestModeTextView() {
        val spannableString = SpannableString(binding.buatAkunTextView.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, binding.buatAkunTextView.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.buatAkunTextView.text = spannableString
    }
}