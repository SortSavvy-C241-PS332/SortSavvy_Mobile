package com.bangkit.sortsavvy.views.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.SessionRepository

class HomeViewModel(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }
}