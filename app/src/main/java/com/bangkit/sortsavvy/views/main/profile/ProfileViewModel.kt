package com.bangkit.sortsavvy.views.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.SessionRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sessionRepository: SessionRepository
) : ViewModel() {

//    private val _isLoggedOut = MutableLiveData<Boolean>()
//    val isLoggedOut: LiveData<Boolean> = _isLoggedOut
    fun logoutSession() {
       viewModelScope.launch {
            sessionRepository.logoutSession()
//                _isLoggedOut.value = true
       }
    }

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }
}