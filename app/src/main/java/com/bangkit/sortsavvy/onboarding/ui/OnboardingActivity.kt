package com.bangkit.sortsavvy.onboarding.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.sortsavvy.onboarding.adapter.OnboardingItemAdapter
import com.bangkit.sortsavvy.onboarding.data.OnboardingItem
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingItemAdapter: OnboardingItemAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineSkipButtonTextView()
        setSharedPreferences()

        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setSharedPreferences() {
        sharedPreferences = getSharedPreferences("SortSavvy", MODE_PRIVATE)
    }

    private fun setUnderLineSkipButtonTextView() {
        val spannableString = SpannableString(binding.skipTextView.text)
        val underlineSpan = UnderlineSpan()

        spannableString.setSpan(underlineSpan, 0, binding.skipTextView.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.skipTextView.text = spannableString
    }

    private fun setOnboardingItems() {
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.onboarding_thumbnail_snap,
                    title = "Snap dan Dapatkan Badge",
                    description = "Kamu bisa dapetin info suatu barang bekas atau sampah yang kamu temukan dengan mudah dan cepat hanya dengan satu snap saja. Bisa kumpulkan badge juga loh ^_^"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.onboarding_thumbnail_belajar_edukasi,
                    title = "Belajar dan Edukasi",
                    description = "Belajar macam-macam jenis sampah yang kamu temukan jadi lebih asik, kamu bisa dapetin info juga terkait barang tersebut sebaiknya didaur ulang menjadi sesuatu ataupun memilih untuk membuangnya dengan cara yang benar."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.onboarding_thumbnail_quiz,
                    title = "Quiz",
                    description = "Ada quiz yang bisa bantu kamu untuk menilai pemahamanmu terkait pengelolaan sampah. Mulai dari pengenalan jenis-jenis sampah yang sering ditemui, pengelolaan sampah, dan hal menarik lainnya."
                )
            )
        )

        binding.onboardingViewPager.adapter = onboardingItemAdapter
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)

                // if current position is the last position
                if (position == onboardingItemAdapter.itemCount - 1) {
                    // hide next button and show get started button
                    binding.nextBtnImageButton.visibility = View.GONE
                    binding.getStartedButton.visibility = View.VISIBLE
                } else {
                    // show next button and hide get started button
                    binding.nextBtnImageButton.visibility = View.VISIBLE
                    binding.getStartedButton.visibility = View.GONE
                }
            }
        })
        (binding.onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.nextBtnImageButton.setOnClickListener {
            if (binding.onboardingViewPager.currentItem + 1 < onboardingItemAdapter.itemCount) {
                binding.onboardingViewPager.currentItem += 1
            } else {
                navigateToWelcomeActivity()
            }
        }

        binding.skipTextView.setOnClickListener {
            navigateToWelcomeActivity()
        }

        binding.getStartedButton.setOnClickListener {
            navigateToWelcomeActivity()
        }
    }

    private fun navigateToWelcomeActivity() {
        sharedPreferences.edit().putBoolean("is_first_time_launch", false).apply()
        val moveIntent = Intent(this@OnboardingActivity, WelcomeActivity::class.java)
        startActivity(moveIntent)
        finish()
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        layoutParams.setMargins(8, 0, 8, 0)
        for (dot in indicators.indices) {
            indicators[dot] = ImageView(applicationContext)
            indicators[dot]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_inactive_background)
                )
                it.layoutParams = layoutParams
                binding.dotIndicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = binding.dotIndicatorContainer.childCount
        for (currentIndicator in 0 until childCount) {
            val imageView = binding.dotIndicatorContainer.getChildAt(currentIndicator) as ImageView
            if (currentIndicator == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_active_background)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_inactive_background)
                )
            }
        }
    }
}