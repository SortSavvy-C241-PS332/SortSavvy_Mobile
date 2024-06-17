package com.bangkit.sortsavvy.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bangkit.sortsavvy.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// initialize the data store
val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    // This function returns the user session
    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            val userId = preferences[USER_ID_KEY]
            val email = preferences[EMAIL_KEY]
            val fullName = preferences[FULL_NAME_KEY]
            val profilePhoto = preferences[PROFILE_PHOTO_KEY]
            val isLogin = preferences[IS_LOGIN_KEY] ?: false
            val isOnboardingViewed = preferences[IS_ONBOARDING_VIEWED_KEY] ?: false
//            if (userId != null && email != null && fullName != null && profilePhoto != null && isLogin) {
                UserModel(userId, email, fullName, profilePhoto, isLogin, isOnboardingViewed)
//            } else null
        }
    }

    // This function saves the user session, enabling the user to stay logged in
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            user.userId?.let { preferences[USER_ID_KEY] = it }
            user.email?.let { preferences[EMAIL_KEY] = it }
            user.fullName?.let { preferences[FULL_NAME_KEY] = it }
            user.profilePhoto?.let { preferences[PROFILE_PHOTO_KEY] = it }
            preferences[IS_LOGIN_KEY] = user.isLogin
            preferences[IS_ONBOARDING_VIEWED_KEY] = user.isOnboardingViewed
        }
    }

    suspend fun removeSession() {
        dataStore.edit { preferences ->
            preferences.clear() // Clear all data, isLogin will be false
            preferences[IS_ONBOARDING_VIEWED_KEY] = true
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val USER_ID_KEY = stringPreferencesKey("userId")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val FULL_NAME_KEY = stringPreferencesKey("fullName")
        private val PROFILE_PHOTO_KEY = stringPreferencesKey("profilePhoto")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val IS_ONBOARDING_VIEWED_KEY = booleanPreferencesKey("isOnboardingViewed")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}