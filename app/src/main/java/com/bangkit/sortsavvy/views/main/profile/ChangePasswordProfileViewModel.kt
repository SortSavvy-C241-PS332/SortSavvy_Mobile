package com.bangkit.sortsavvy.views.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.data.repository.ProfileRepository

class ChangePasswordProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _invalidValidation = MutableLiveData<String>()
    val invalidValidation: LiveData<String> = _invalidValidation

    private lateinit var _password: String
    private lateinit var _newPassword: String
    private lateinit var _confirmNewPassword: String

    fun validateUserForm(userData: Map<String, String>): Boolean {
        initializeUserData(userData)
        println("userData -> $userData")

        if (_password.isEmpty() || _newPassword.isEmpty() || _confirmNewPassword.isEmpty()) {
            _invalidValidation.value = "Mohon isi semua kolom yang tersedia"
            return false
        }
        if (_newPassword != _confirmNewPassword) {
            _invalidValidation.value = "Password baru dan konfirmasi password baru tidak sama"
            return false
        }
        println("userData true -> $userData")
        return true
    }

    private fun initializeUserData(userData: Map<String, String>) {
        _password = userData["password"].toString()
        _newPassword = userData["newPassword"].toString()
        _confirmNewPassword = userData["confirmNewPassword"].toString()
    }

    fun changePassword(userID: Int, userData: Map<String, String>) = profileRepository.updateUserPassword(
        userID,
        _password,
        _newPassword,
    )
}