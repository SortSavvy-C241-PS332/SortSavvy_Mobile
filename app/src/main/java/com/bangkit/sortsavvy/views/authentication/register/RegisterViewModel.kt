package com.bangkit.sortsavvy.views.authentication.register

import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.data.repository.RegisterRepository

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    fun registerAccount(name: String, email: String, password: String) = repository.registerAccount(name, email, password)
}