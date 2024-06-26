package com.bangkit.sortsavvy.di

import android.content.Context
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.pref.userDataStore
import com.bangkit.sortsavvy.data.remote.retrofit.ApiConfig
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import com.bangkit.sortsavvy.data.repository.LoginRepository
import com.bangkit.sortsavvy.data.repository.RegisterRepository
import com.bangkit.sortsavvy.data.repository.SessionRepository

object AuthenticationInjection {

    private fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    fun provideLoginRepository(context: Context): LoginRepository {
//        val userPreferences = UserPreferences.getInstance(context.userDataStore)
        val apiService = provideApiService()
//        return LoginRepository.getInstance(apiService, userPreferences)
        return LoginRepository.getInstance(apiService)
    }

    fun provideSessionRepository(context: Context): SessionRepository {
        val userPreferences = UserPreferences.getInstance(context.userDataStore)
        return SessionRepository.getInstance(userPreferences)
    }

    fun provideRegisterRepository() : RegisterRepository {
        val apiService = provideApiService()
        return RegisterRepository.getInstance(apiService)
    }
}