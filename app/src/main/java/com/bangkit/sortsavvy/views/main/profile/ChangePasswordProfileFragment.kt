package com.bangkit.sortsavvy.views.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.databinding.FragmentChangePasswordProfileBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bumptech.glide.Glide

class ChangePasswordProfileFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordProfileBinding
    private lateinit var viewModel: ChangePasswordProfileViewModel

    private lateinit var userData: Map<String, String>
    private var currentUser: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_change_password_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangePasswordProfileBinding.bind(view)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ChangePasswordProfileViewModel::class.java]


        currentUser = arguments?.getParcelable(SettingsProfileFragment.EXTRA_USER_DATA)
        if (currentUser != null) {
            setUserProfileDataToView(currentUser)
            println("getSession -> $currentUser")
        }

        setUpButtonListener()

        viewModel.invalidValidation.observe(viewLifecycleOwner) { message ->
            ViewComponentUtil.showToast(this.requireContext(), message)
        }
    }

    private fun setUserProfileDataToView(userModel: UserModel?) {
        binding.emailTextView.text = userModel?.email
        Glide.with(this)
            .load(userModel?.profilePhoto)
            .placeholder(R.drawable.profile_thumbnail_avatar_syella)
            .into(binding.avatarImageView)
    }

    private fun setUpButtonListener() {
        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener {
            val isValid = validateUserForm()
            if (isValid) currentUser?.userId?.let { userId ->
                updateUserPassword(userId)
            }
        }
    }

    private fun updateUserPassword(userID: Int) {
        println("updateUserPassword -> $userID")
        println("updateUserPassword -> $userData")
        viewModel.changePassword(userID, userData).observe(viewLifecycleOwner) { resultState ->
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
//                        binding.saveButton.isEnabled = false
//                        ViewComponentUtil.showToast(this.requireContext(), resultState.data.mesasge)
                    }
                    is ResultState.Success -> {
//                        binding.progressBar.visibility = View.GONE
//                        binding.saveButton.isEnabled = true

//                        ViewComponentUtil.showToast(this.requireContext(), resultState.data.message)
//                        findNavController().navigateUp()
                    }
                    is ResultState.Error -> {
//                        binding.progressBar.visibility = View.GONE
                        binding.saveButton.isEnabled = true
                        ViewComponentUtil.showToast(
                            this.requireContext(),
                            resultState.errorMessage
                        )
                    }
                }
            }
        }
    }

    private fun validateUserForm(): Boolean {
        userData = getDataUserFromForm()
        val isValid: Boolean = viewModel.validateUserForm(userData)
        return isValid
    }

    private fun getDataUserFromForm(): Map<String, String> {
        val data = mutableMapOf<String, String>()
        data["password"] = binding.passwordEditText.text.toString()
        data["newPassword"] = binding.newPasswordEditText.text.toString()
        data["confirmNewPassword"] = binding.confirmNewPasswordEditText.text.toString()

//        data["email"] = binding.emailEditText.text.toString()
//        data["password"] = binding.passwordEditText.text.toString()
//        data["confirmPassword"] = binding.confirmPasswordEditText.text.toString()

        println(data)

        return data
    }

    companion object {
        fun newInstance() = ChangePasswordProfileFragment()
    }
}