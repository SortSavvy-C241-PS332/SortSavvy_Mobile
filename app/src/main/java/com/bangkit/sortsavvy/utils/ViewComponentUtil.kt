package com.bangkit.sortsavvy.utils

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.Toast

object ViewComponentUtil {
    fun createUnderlinedSpannableString(stringText: String) : SpannableString {
        val spannableString = SpannableString(stringText)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, stringText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}