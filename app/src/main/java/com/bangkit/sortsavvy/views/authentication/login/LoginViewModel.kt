package com.bangkit.sortsavvy.views.authentication.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.LoginRepository
import com.bangkit.sortsavvy.data.repository.OnboardingRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _isSessionSaved = MutableLiveData<Boolean>()
//    val isSessionSaved: LiveData<Boolean> = _isSessionSaved

    fun setSession(user: UserModel) {
        viewModelScope.launch {
            loginRepository.saveSession(user)
            _isSessionSaved.value = true
            checkReadyToNavigate()
        }
    }

    private val _invalidValidation = MutableLiveData<String>()
    val invalidValidation: LiveData<String> = _invalidValidation
    fun validateUserForm(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            _invalidValidation.value = "Mohon isi semua kolom yang tersedia"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _invalidValidation.value = "Email tidak valid, mohon isi dengan benar"
            return false
        }
        return true
    }

    private val _isOnboardingViewed = MutableLiveData<Boolean>()
//    val isOnboardingViewed: LiveData<Boolean> = _isOnboardingViewed

    fun setOnboardingViewedStatus(status: Boolean) {
        viewModelScope.launch {
            onboardingRepository.setOnboardingViewedStatus(status)
            _isOnboardingViewed.value = status
        }
    }

    private val _isReadyToNavigate = MutableLiveData<Boolean>()
    val isReadyToNavigate: LiveData<Boolean> = _isReadyToNavigate

    private fun checkReadyToNavigate() {
        if (_isSessionSaved.value == true && _isOnboardingViewed.value == true) {
            _isReadyToNavigate.value = true
        }
    }

    fun loginAccount(email: String, password: String) = loginRepository.loginAccount(email, password)
//    fun getOnboardingPreferences(): LiveData<Boolean> {
//        return onboardingRepository.getOnboardingStatus().asLiveData()
//    }
}
