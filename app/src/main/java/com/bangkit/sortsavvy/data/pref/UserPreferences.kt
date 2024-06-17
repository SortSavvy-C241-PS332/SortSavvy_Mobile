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
            UserModel(
                preferences[USER_ID_KEY] ?: "",
                preferences[EMAIL_KEY] ?: "",
                preferences[FULL_NAME_KEY] ?: "",
                preferences[PROFILE_PHOTO_KEY],
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    // This function saves the user session, enabling the user to stay logged in
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = user.userId
            preferences[EMAIL_KEY] = user.email
            preferences[FULL_NAME_KEY] = user.fullName
            if (user.profilePhoto != null) preferences[PROFILE_PHOTO_KEY] = user.profilePhoto
            preferences[IS_LOGIN_KEY] = user.isLogin
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

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}