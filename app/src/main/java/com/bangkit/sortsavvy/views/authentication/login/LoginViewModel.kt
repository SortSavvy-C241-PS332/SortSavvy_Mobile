package com.bangkit.sortsavvy.views.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _isSessionSaved = MutableLiveData<Boolean>()
    val isSessionSaved: LiveData<Boolean> = _isSessionSaved

    fun setSession(user: UserModel) {
        viewModelScope.launch {
            loginRepository.saveSession(user)
            _isSessionSaved.value = true
        }
    }

    fun loginAccount(email: String, password: String) = loginRepository.loginAccount(email, password)
}