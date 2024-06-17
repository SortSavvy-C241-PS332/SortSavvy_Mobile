package com.bangkit.sortsavvy.views.authentication.register

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.remote.response.UserDataRegister

import com.bangkit.sortsavvy.databinding.ActivityRegisterBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.authentication.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    private var countdownTimer: CountDownTimer? = null

    private lateinit var userData: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()
        supportActionBar?.hide()

        val viewModelFactory= ViewModelFactory.getInstance(this@RegisterActivity)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]

        binding.registerButton.setOnClickListener {
            validateUserForm()
        }

        binding.loginNowTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel.invalidValidation.observe(this@RegisterActivity) { message ->
            ViewComponentUtil.showToast(this@RegisterActivity, message)
        }
    }

    private fun validateUserForm() {
        userData = getDataUserFromForm()
        val isValid: Boolean = viewModel.validateUserForm(userData)
        if (isValid) registerAccount()
    }

    private fun registerAccount() {
        viewModel.registerAccount(userData).observe(this) { resultState ->
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.registerButton.isEnabled = false
                    }
                    is ResultState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.registerButton.isEnabled = true

                        ViewComponentUtil.showToast(this@RegisterActivity, resultState.data.message)
                        navigateToLoginScreen(resultState.data.userDataRegister)
                    }
                    is ResultState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.registerButton.isEnabled = true
                        ViewComponentUtil.showToast(
                            this@RegisterActivity,
                            resultState.errorMessage
                        )
                    }
                }
            }
        }
    }

    private fun navigateToLoginScreen(userData: UserDataRegister) {
        binding.statusMessageTextView.text = "Email ${userData.email} berhasil terdaftar"
        binding.statusMessageTextView.visibility = View.VISIBLE

        countdownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countdownTextView.visibility = View.VISIBLE
                binding.countdownTextView.text = "Redirecting to login page in ${millisUntilFinished / 1000} seconds..."
            }
            override fun onFinish() {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }

        countdownTimer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer?.cancel()
    }

    private fun getDataUserFromForm(): Map<String, String> {
        val data = mutableMapOf<String, String>()
        data["name"] = binding.nameEditText.text.toString()
        data["email"] = binding.emailEditText.text.toString()
        data["password"] = binding.passwordEditText.text.toString()
        data["confirmPassword"] = binding.confirmPasswordEditText.text.toString()

        println(data)

        return data
    }

    private fun setUnderLineGuestModeTextView() {
        val valueString = binding.loginNowTextView.text
        val spannableString = ViewComponentUtil.createUnderlinedSpannableString(valueString.toString())

        binding.loginNowTextView.text = spannableString
    }
}