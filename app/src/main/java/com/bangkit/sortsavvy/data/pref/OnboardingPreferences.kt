package com.bangkit.sortsavvy.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// initialize the data store
val Context.onboardingDataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding_preferences")

class OnboardingPreferences(private val dataStore: DataStore<Preferences>) {

    // This function returns false (default is false for new users), it means the onboarding screen will be shown
    fun getOnboardingViewedStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_ONBOARDING_VIEWED_KEY] ?: false
        }
    }

    // This function sets the onboarding status to true after the onboarding screen is viewed
    suspend fun setOnboardingViewedStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ONBOARDING_VIEWED_KEY] = status
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: OnboardingPreferences? = null

        private val IS_ONBOARDING_VIEWED_KEY = booleanPreferencesKey("isOnboarding")

        fun getInstance(dataStore: DataStore<Preferences>): OnboardingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = OnboardingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}