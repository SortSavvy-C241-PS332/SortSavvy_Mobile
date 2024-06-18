package com.bangkit.sortsavvy.views.main.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.model.UserProfileModel
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.repository.ProfileRepository
import com.bangkit.sortsavvy.data.repository.SessionRepository

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

    fun updateUserProfile(userID: String, newDataUser: UserProfileModel) = profileRepository.updateUserProfile(userID, newDataUser)
}