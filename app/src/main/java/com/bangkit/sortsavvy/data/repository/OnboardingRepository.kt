package com.bangkit.sortsavvy.data.repository

import com.bangkit.sortsavvy.data.pref.OnboardingPreferences
import kotlinx.coroutines.flow.Flow

class OnboardingRepository(private val onboardingPref: OnboardingPreferences) {

    suspend fun setOnboardingViewedStatus(status: Boolean) {
        onboardingPref.setOnboardingViewedStatus(status)
    }

    fun getOnboardingStatus(): Flow<Boolean> {
        return onboardingPref.getOnboardingViewedStatus()
    }

    companion object {
        @Volatile
        private var instance: OnboardingRepository? = null

        fun getInstance(onboardingPref: OnboardingPreferences): OnboardingRepository {
            return instance ?: synchronized(this) {
                instance ?: OnboardingRepository(onboardingPref).also { instance = it }
            }
        }
    }
}