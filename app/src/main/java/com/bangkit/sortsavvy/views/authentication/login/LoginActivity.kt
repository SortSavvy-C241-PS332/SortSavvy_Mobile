package com.bangkit.sortsavvy.views.authentication.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.remote.response.UserDataLogin
import com.bangkit.sortsavvy.databinding.ActivityLoginBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.authentication.register.RegisterActivity
import com.bangkit.sortsavvy.views.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()
        supportActionBar?.hide()

        val viewModelFactory= ViewModelFactory.getInstance(this@LoginActivity)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding.loginButton.setOnClickListener {
            validateUserForm()
        }

        binding.buatAkunTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel.invalidValidation.observe(this) { message ->
            ViewComponentUtil.showToast(this@LoginActivity, message)
        }
    }

    private fun validateUserForm() {
        val (email, password) = getDataUserFromForm()
        val isValid: Boolean = viewModel.validateUserForm(email, password)
        if (isValid) loginAccount(email, password)
    }

    private fun loginAccount(email: String, password: String) {
        viewModel.loginAccount(email, password).observe(this@LoginActivity) { resultState ->
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.loginButton.isEnabled = false
                    }
                    is ResultState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.loginButton.isEnabled = true
                        // bug resultState.data.message is not found
                        val message = "Selamat datang ${resultState.data.userDataLogin.fullName}"
                        ViewComponentUtil.showToast(this@LoginActivity, message)

                        val user = createUserModelSession(resultState.data.userDataLogin)
                        viewModel.setSession(user)
                        viewModel.setOnboardingViewedStatus(true)
                    }
                    is ResultState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.loginButton.isEnabled = true
                        ViewComponentUtil.showToast(this@LoginActivity, resultState.errorMessage)
                    }
                }
            }
        }

        viewModel.isReadyToNavigate.observe(this@LoginActivity) { isReadyToNavigate ->
            if (isReadyToNavigate) {
                navigateToHomeScreen()
            }
        }
    }

    private fun createUserModelSession(userData: UserDataLogin):  UserModel {
        val userID = "123"
        val email = userData.email
        val fullName = userData.fullName
        val profilePhoto = userData.profilePhoto
        val isLogin = true

        return UserModel(userID, email, fullName, profilePhoto,isLogin)
    }

    private fun getDataUserFromForm(): Pair<String, String> {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return Pair(email, password)
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun setUnderLineGuestModeTextView() {
        val valueString = binding.buatAkunTextView.text
        val spannableString = ViewComponentUtil.createUnderlinedSpannableString(valueString.toString())

        binding.buatAkunTextView.text = spannableString
    }
}