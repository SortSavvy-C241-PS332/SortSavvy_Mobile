package com.bangkit.sortsavvy.onboarding.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dicoding.sortsavvy.R

class WelcomeActivity : AppCompatActivity() {

    private lateinit var guestModeTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        underLineGuestModeTv()
    }

    private fun underLineGuestModeTv() {
        guestModeTv = findViewById(R.id.guestModeTv)
        val spannableString = SpannableString(guestModeTv.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, guestModeTv.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        guestModeTv.text = spannableString
    }
}