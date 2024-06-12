package com.bangkit.sortsavvy.views.authentication.register

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.views.authentication.login.LoginActivity
import com.bangkit.sortsavvy.databinding.ActivityRegisterBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    private var countdownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLineGuestModeTextView()
        supportActionBar?.hide()

        val viewModelFactory= ViewModelFactory.getInstance(this@RegisterActivity)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]

        binding.registerButton.setOnClickListener {
            registerAccount()
        }

        binding.loginNowTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerAccount() {
        val (name, email, password) = getDataUserFromForm()

        viewModel.registerAccount(name, email, password).observe(this@RegisterActivity) { resultState ->
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.registerButton.isEnabled = false
                    }
                    is ResultState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.registerButton.isEnabled = true

//                        ViewComponentUtil.showToast(this@RegisterActivity, resultState.data.message)
                        navigateToLoginScreen()
                    }
                    is ResultState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.registerButton.isEnabled = true
                        ViewComponentUtil.showToast(this@RegisterActivity, resultState.errorMessage)
                    }
                }
            }
        }
    }

    private fun navigateToLoginScreen() {
//        binding.statusMessageTextView.text = "Email ${user.email} berhasil terdaftar"
        val email = binding.emailEditText.text.toString()
        binding.statusMessageTextView.text = "Email $email berhasil terdaftar"
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
        finish()
    }

    private fun getDataUserFromForm(): Triple<String, String, String> {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        return Triple(name, email, password)
    }

    private fun setUnderLineGuestModeTextView() {
        val valueString = binding.loginNowTextView.text
        val spannableString = ViewComponentUtil.createUnderlinedSpannableString(valueString.toString())

        binding.loginNowTextView.text = spannableString
    }
}