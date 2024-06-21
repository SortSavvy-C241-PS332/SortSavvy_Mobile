package com.bangkit.sortsavvy.views.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.OnboardingItem
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.SessionRepository
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _onboardingItems = MutableLiveData<List<OnboardingItem>>()
    val onboardingItems: LiveData<List<OnboardingItem>> = _onboardingItems

//    private val _isOnboardingViewed = MutableLiveData<Boolean>()
//    val isOnboardingViewed: LiveData<Boolean> = _isOnboardingViewed

    fun loadOnboardingItems() {
        val items = listOf(
            OnboardingItem(
                onboardingImage = R.drawable.onboarding_camera,
                title = "Snap dan Sort",
                description = "Identifikasi dan pilah sampah dengan mudah hanya dengan satu foto"
            ),
            OnboardingItem(
                onboardingImage = R.drawable.onboarding_explore,
                title = "Jelajah Dunia Sampah",
                description = "Serunya menjelajahi dunia pengelolaan sampah melalui edukasi interaktif!"
            ),
            OnboardingItem(
                onboardingImage = R.drawable.onboarding_trophy,
                title = "Tantangan dan Lencana",
                description = "Selesaikan tantangan, kumpulkan lencana keren, dan pantau aksimu untuk lingkungan!"
            )
        )
        _onboardingItems.value = items
    }

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }

//    private val _isSessionSaved = MutableLiveData<Boolean>()
//    val isSessionSaved: LiveData<Boolean> = _isSessionSaved

    fun setSession(userModel: UserModel) {
        viewModelScope.launch {
            sessionRepository.saveSession(userModel)
//            _isSessionSaved.value = true
        }
    }

//    fun getOnboardingPreferences(): LiveData<Boolean> {
//        return onboardingRepository.getOnboardingStatus().asLiveData()
//    }

//    fun setOnboardingViewedStatus(status: Boolean) {
//        viewModelScope.launch {
//            onboardingRepository.setOnboardingViewedStatus(status)
//            _isOnboardingViewed.value = status
//        }
//    }
}