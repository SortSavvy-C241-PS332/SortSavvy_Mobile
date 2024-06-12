package com.bangkit.sortsavvy.views.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.OnboardingItem
import com.bangkit.sortsavvy.data.repository.OnboardingRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(private val onboardingRepository: OnboardingRepository) : ViewModel() {
    private val _isOnboardingViewed = MutableLiveData<Boolean>()
    val isOnboardingViewed: LiveData<Boolean> = _isOnboardingViewed

//    fun getOnboardingPreferences(): LiveData<Boolean> {
//        return onboardingRepository.getOnboardingStatus().asLiveData()
//    }

    fun setOnboardingViewedStatus(status: Boolean) {
        viewModelScope.launch {
            onboardingRepository.setOnboardingViewedStatus(status)
            _isOnboardingViewed.value = status
        }
    }
}