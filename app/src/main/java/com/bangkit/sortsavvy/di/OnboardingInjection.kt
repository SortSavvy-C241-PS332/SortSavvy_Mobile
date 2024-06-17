package com.bangkit.sortsavvy.di

import android.content.Context
import com.bangkit.sortsavvy.data.pref.OnboardingPreferences
import com.bangkit.sortsavvy.data.pref.onboardingDataStore
import com.bangkit.sortsavvy.data.repository.OnboardingRepository

//object OnboardingInjection {
//    fun provideOnboardingRepository(context: Context): OnboardingRepository {
//        val onboardingPreferences = OnboardingPreferences.getInstance(context.onboardingDataStore)
//        return OnboardingRepository.getInstance(onboardingPreferences)
//    }
//}