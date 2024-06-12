package com.bangkit.sortsavvy.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.pref.userDataStore
import com.bangkit.sortsavvy.di.AuthenticationInjection
import com.bangkit.sortsavvy.di.OnboardingInjection
import com.bangkit.sortsavvy.views.authentication.login.LoginViewModel
import com.bangkit.sortsavvy.views.authentication.register.RegisterViewModel
import com.bangkit.sortsavvy.views.onboarding.OnboardingViewModel
import com.bangkit.sortsavvy.views.welcome.WelcomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(OnboardingViewModel::class.java) -> {
                val onboardingRepository = OnboardingInjection.provideOnboardingRepository(context)
                OnboardingViewModel(onboardingRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val loginRepository = AuthenticationInjection.provideLoginRepository(context)
                LoginViewModel(loginRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                val registerRepository = AuthenticationInjection.provideRegisterRepository()
                RegisterViewModel(registerRepository) as T
            }
            modelClass.isAssignableFrom(WelcomeViewModel::class.java) -> {
                val onboardingRepository = OnboardingInjection.provideOnboardingRepository(context)
                WelcomeViewModel(onboardingRepository) as T
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