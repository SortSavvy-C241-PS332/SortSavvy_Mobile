package com.bangkit.sortsavvy.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.LoginRepository
import com.bangkit.sortsavvy.data.repository.OnboardingRepository
import com.bangkit.sortsavvy.data.repository.SessionRepository
import kotlinx.coroutines.launch

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