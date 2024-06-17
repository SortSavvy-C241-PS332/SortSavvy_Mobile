package com.bangkit.sortsavvy.views.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.OnboardingItem
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.OnboardingRepository
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
                onboardingImage = R.drawable.onboarding_thumbnail_snap,
                title = "Snap dan Dapatkan Badge",
                description = "Kamu bisa dapetin info suatu barang bekas atau sampah yang kamu temukan dengan mudah dan cepat hanya dengan satu snap saja. Bisa kumpulkan badge juga loh ^_^"
            ),
            OnboardingItem(
                onboardingImage = R.drawable.onboarding_thumbnail_belajar_edukasi,
                title = "Belajar dan Edukasi",
                description = "Belajar macam-macam jenis sampah yang kamu temukan jadi lebih asik, kamu bisa dapetin info juga terkait barang tersebut sebaiknya didaur ulang menjadi sesuatu ataupun memilih untuk membuangnya dengan cara yang benar."
            ),
            OnboardingItem(
                onboardingImage = R.drawable.onboarding_thumbnail_quiz,
                title = "Quiz",
                description = "Ada quiz yang bisa bantu kamu untuk menilai pemahamanmu terkait pengelolaan sampah. Mulai dari pengenalan jenis-jenis sampah yang sering ditemui, pengelolaan sampah, dan hal menarik lainnya."
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