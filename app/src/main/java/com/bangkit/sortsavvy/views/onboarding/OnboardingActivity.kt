package com.bangkit.sortsavvy.views.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.OnboardingItemAdapter
import com.bangkit.sortsavvy.data.model.OnboardingItem
import com.bangkit.sortsavvy.data.pref.OnboardingPreferences
import com.bangkit.sortsavvy.databinding.ActivityOnboardingBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.main.MainActivity
import com.bangkit.sortsavvy.views.welcome.WelcomeActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var preferences: OnboardingPreferences

    private lateinit var adapter: OnboardingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)

        setUnderLineSkipButtonTextView()
        supportActionBar?.hide()

        val viewModelFactory = ViewModelFactory.getInstance(this@OnboardingActivity)
        viewModel = ViewModelProvider(this, viewModelFactory)[OnboardingViewModel::class.java]

        viewModel.getOnboardingPreferences().observe(this) { isOnboardingViewedStatus ->
            if (isOnboardingViewedStatus == true) {
                println("ActivityOnboardingBinding onboarding viewed -> $isOnboardingViewedStatus")
                Log.d(TAG, isOnboardingViewedStatus.toString())
                navigateToMainActivityScreen()
            } else {
                setContentView(binding.root)
            }
        }

        viewModel.loadOnboardingItems()
        viewModel.onboardingItems.observe(this) { listOnboardingItems ->
            if (listOnboardingItems != null) {
                setOnboardingAdapter(listOnboardingItems)
                setOnboardingItems()
                setButtonNavigationListener()
                setupIndicators()
                setCurrentIndicator(0)
            } else {
                Log.e(TAG, "Onboarding items are null")
            }
        }
    }

    private fun setUnderLineSkipButtonTextView() {
        val valueString = binding.skipTextView.text
        val spannableString = ViewComponentUtil.createUnderlinedSpannableString(valueString.toString())

        binding.skipTextView.text = spannableString
    }

    private fun setOnboardingAdapter(listOnboardingItems: List<OnboardingItem>) {
        adapter = OnboardingItemAdapter(listOnboardingItems)
        binding.onboardingViewPager.adapter = adapter
    }

    private fun setButtonNavigationListener() {
        binding.nextBtnImageButton.setOnClickListener {
            if (binding.onboardingViewPager.currentItem + 1 < adapter.itemCount) {
                binding.onboardingViewPager.currentItem += 1
            }
//            udah diatur di binding.getStartedButton.setOnClickListener to navigateToWelcomeActivity()
//            else {
//                navigateToWelcomeActivity()
//            }
        }
        binding.skipTextView.setOnClickListener {
            navigateToWelcomeActivity()
        }
        binding.getStartedButton.setOnClickListener {
            navigateToWelcomeActivity()
        }
    }

    private fun setOnboardingItems() {
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)

                // if current position is the last position
                if (position == adapter.itemCount - 1) {
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
        val viewPagerChild = binding.onboardingViewPager.getChildAt(0)
        if (viewPagerChild is RecyclerView) {
            viewPagerChild.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        } else {
            Log.e(TAG, "ViewPager child is not a RecyclerView")
        }
    }

//    private fun navigateToWelcomeActivity() {
//        viewModel.setOnboardingViewedStatus(true)
//        viewModel.isOnboardingViewed.observe(this) { isOnboardingViewed ->
//            if (isOnboardingViewed) {
//                val moveIntent = Intent(this@OnboardingActivity, WelcomeActivity::class.java)
//                startActivity(moveIntent)
//                finish()
//            }
//        }
//    }

    private fun navigateToWelcomeActivity() {
        val moveIntent = Intent(this@OnboardingActivity, WelcomeActivity::class.java)
        startActivity(moveIntent)
        finish()
    }

    private fun navigateToMainActivityScreen() {
        val moveIntent = Intent(this@OnboardingActivity, MainActivity::class.java)
        startActivity(moveIntent)
        finish()
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        layoutParams.setMargins(8, 0, 8, 0)
        for (dot in indicators.indices) {
            indicators[dot] = ImageView(applicationContext)
            indicators[dot]?.let { inActiveImageView ->
                inActiveImageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_inactive_background)
                )
                inActiveImageView.layoutParams = layoutParams
                binding.dotIndicatorContainer.addView(inActiveImageView)
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

    companion object {
        private const val TAG = "OnboardingActivity"
    }
}