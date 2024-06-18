package com.bangkit.sortsavvy.di

import android.content.Context
import com.bangkit.sortsavvy.data.remote.retrofit.ApiConfig
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import com.bangkit.sortsavvy.data.repository.LoginRepository
import com.bangkit.sortsavvy.data.repository.ProfileRepository

object UserDataInjection {
    private fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    fun provideProfileRepository(context: Context): ProfileRepository {
        val apiService = UserDataInjection.provideApiService()
        return ProfileRepository.getInstance(apiService)
    }
}