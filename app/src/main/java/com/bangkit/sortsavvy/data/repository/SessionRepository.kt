package com.bangkit.sortsavvy.data.repository

import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class SessionRepository(
    private val userPreferences: UserPreferences
) {
    fun getSession(): Flow<UserModel> {
        return userPreferences.getSession()
    }

    suspend fun saveSession(userModel: UserModel) {
        userPreferences.saveSession(userModel)
    }

    suspend fun logoutSession() {
        userPreferences.removeSession()
    }

    companion object {
        @Volatile
        private var instance: SessionRepository? = null
        fun getInstance(userPref: UserPreferences) =
            instance ?: synchronized(this) {
                instance ?: SessionRepository(userPref)
            }.also { instance = it }
    }
}