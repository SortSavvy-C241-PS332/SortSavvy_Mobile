package com.bangkit.sortsavvy.views.authentication.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.views.authentication.register.RegisterActivity
import com.bangkit.sortsavvy.databinding.ActivityLoginBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
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
            loginAccount()
        }

        binding.buatAkunTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginAccount() {
        val (email, password) = getDataUserFromForm()

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
//                        val message = "Selamat datang ${resultState.data.loginResult.name}"
//                        ViewComponentUtil.showToast(this@LoginActivity, message)

                        val user = createUserModelSession(resultState)
                        viewModel.setSession(user)
                    }
                    is ResultState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.loginButton.isEnabled = true
                        ViewComponentUtil.showToast(this@LoginActivity, resultState.errorMessage)
                    }
                }
            }
        }

        viewModel.isSessionSaved.observe(this) { isSessionSaved ->
            if (isSessionSaved) {
                navigateToHomeScreen()
            }
        }
    }

    private fun createUserModelSession(resultState: ResultState<Unit>):  UserModel {
        val email = "resultState.data.loginResult.email"
        val token = "resultState.data.loginResult.token"
        val isLogin = true
        return UserModel(email, token, isLogin)
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