package com.bangkit.sortsavvy.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.SessionRepository

class MainViewModel(
//    private val onboardingRepository: OnboardingRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }

//    fun setSession(userModel: UserModel) {
//        viewModelScope.launch {
//            sessionRepository.saveSession(userModel)
//        }
//    }
}