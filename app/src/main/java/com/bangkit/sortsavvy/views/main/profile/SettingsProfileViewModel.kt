package com.bangkit.sortsavvy.views.main.profile

import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.model.UserProfileModel
import com.bangkit.sortsavvy.data.repository.ProfileRepository
import com.bangkit.sortsavvy.data.repository.SessionRepository
import kotlinx.coroutines.launch

class SettingsProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> = _currentImageUri

    fun setCurrentImageUri(uri: Uri?) {
        _currentImageUri.postValue(uri)
    }

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }

    fun updateSession(userModel: UserModel) {
        viewModelScope.launch {
            sessionRepository.saveSession(userModel)
        }
    }

    private val _invalidValidation = MutableLiveData<String>()
    val invalidValidation: LiveData<String> = _invalidValidation
    fun validateUserForm(fullName: String, email: String, password: String): Boolean {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _invalidValidation.value = "Mohon isi semua kolom yang tersedia"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _invalidValidation.value = "Email tidak valid, mohon isi dengan benar"
            return false
        }
        return true
    }

    fun updateUserProfile(userID: Int, newDataUser: UserProfileModel) = profileRepository.updateUserProfile(userID, newDataUser)
}