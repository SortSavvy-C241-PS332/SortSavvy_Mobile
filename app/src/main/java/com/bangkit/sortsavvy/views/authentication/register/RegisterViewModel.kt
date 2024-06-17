package com.bangkit.sortsavvy.views.authentication.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.repository.RegisterRepository
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    private val _invalidValidation = MutableLiveData<String>()
    val invalidValidation: LiveData<String> = _invalidValidation

    private lateinit var _name: String
    private lateinit var _email: String
    private lateinit var _password: String
    private lateinit var _confirmPassword: String

    fun validateUserForm(userData: Map<String, String>): Boolean {
        initializeUserData(userData)

        if (_name.isEmpty() || _email.isEmpty() || _password.isEmpty() || _confirmPassword.isEmpty()) {
            _invalidValidation.value = "Mohon isi semua kolom yang tersedia"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            _invalidValidation.value = "Email tidak valid"
            return false
        }
        if (_password != _confirmPassword) {
            _invalidValidation.value = "Password dan konfirmasi password tidak sama"
            return false
        }
        return true
    }

    private fun initializeUserData(userData: Map<String, String>) {
        _name = userData["name"].toString()
        _email = userData["email"].toString()
        _password = userData["password"].toString()
        _confirmPassword = userData["confirmPassword"].toString()
    }

    fun registerAccount(userData: Map<String, String>) = repository.registerAccount(
        _name,
        _email,
        _password,
        _confirmPassword
    )
}