package com.bangkit.sortsavvy.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.di.AuthenticationInjection
import com.bangkit.sortsavvy.di.UserDataInjection
//import com.bangkit.sortsavvy.di.OnboardingInjection
import com.bangkit.sortsavvy.views.authentication.login.LoginViewModel
import com.bangkit.sortsavvy.views.authentication.register.RegisterViewModel
import com.bangkit.sortsavvy.views.main.MainViewModel
import com.bangkit.sortsavvy.views.main.challenge.ChallengeViewModel
import com.bangkit.sortsavvy.views.main.profile.ProfileViewModel
import com.bangkit.sortsavvy.views.main.profile.SettingsProfileViewModel
import com.bangkit.sortsavvy.views.main.snap.SnapResultViewModel
import com.bangkit.sortsavvy.views.main.snap.SnapViewModel
import com.bangkit.sortsavvy.views.onboarding.OnboardingViewModel
import com.bangkit.sortsavvy.views.welcome.WelcomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(OnboardingViewModel::class.java) -> {
                val sessionRepository = AuthenticationInjection.provideSessionRepository(context)
                OnboardingViewModel(sessionRepository) as T
            }
//            modelClass.isAssignableFrom(WelcomeViewModel::class.java) -> {
//                val onboardingRepository = OnboardingInjection.provideOnboardingRepository(context)
//                WelcomeViewModel(onboardingRepository) as T
//            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                val registerRepository = AuthenticationInjection.provideRegisterRepository()
                RegisterViewModel(registerRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val loginRepository = AuthenticationInjection.provideLoginRepository(context)
                val sessionRepository = AuthenticationInjection.provideSessionRepository(context)
//                val onboardingRepository = OnboardingInjection.provideOnboardingRepository(context)
//                LoginViewModel(loginRepository, sessionRepository, onboardingRepository) as T
                LoginViewModel(loginRepository, sessionRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                val onboardingRepository = OnboardingInjection.provideOnboardingRepository(context)
                val sessionRepository = AuthenticationInjection.provideSessionRepository(context)
//                MainViewModel(onboardingRepository, sessionRepository) as T
                MainViewModel(sessionRepository) as T
//                MainViewModel() as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                val sessionRepository = AuthenticationInjection.provideSessionRepository(context)
                ProfileViewModel(sessionRepository) as T
            }
            modelClass.isAssignableFrom(SettingsProfileViewModel::class.java) -> {
                val profileRepository = UserDataInjection.provideProfileRepository(context)
                val sessionRepository = AuthenticationInjection.provideSessionRepository(context)
                SettingsProfileViewModel(profileRepository, sessionRepository) as T
            }



            modelClass.isAssignableFrom(SnapViewModel::class.java) -> {
                SnapViewModel() as T
            }

            modelClass.isAssignableFrom(SnapResultViewModel::class.java) -> {
                SnapResultViewModel() as T
            }

            modelClass.isAssignableFrom(ChallengeViewModel::class.java) -> {
                ChallengeViewModel() as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}. Ensure the ViewModel class is correctly registered.")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null) {
                synchronized(ViewModelFactory::class.java) {
                        instance = ViewModelFactory(context.applicationContext)
                }
            }
            return instance as ViewModelFactory
        }
    }
}