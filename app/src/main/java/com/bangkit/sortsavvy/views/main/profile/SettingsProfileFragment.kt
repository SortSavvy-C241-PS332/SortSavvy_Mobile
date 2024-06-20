package com.bangkit.sortsavvy.views.main.profile

import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.model.UserProfileModel
import com.bangkit.sortsavvy.data.remote.response.Data
import com.bangkit.sortsavvy.databinding.FragmentSettingsProfileBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ImageUtil
import com.bangkit.sortsavvy.utils.ImageUtil.reduceFileImage
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class SettingsProfileFragment : Fragment() {

    private lateinit var binding: FragmentSettingsProfileBinding
    private lateinit var viewModel: SettingsProfileViewModel

    private lateinit var currentUser: UserModel
    private lateinit var userData: Triple<String, String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_settings_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsProfileBinding.bind(view)

        val userModel: UserModel? = arguments?.getParcelable(ProfileFragment.EXTRA_USER_DATA)
        if (userModel != null) {
            setUserProfileDataToView(userModel)
        }

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[SettingsProfileViewModel::class.java]

        setUpButtonCardItem()
        setUpButtonListener()

        viewModel.currentImageUri.observe(viewLifecycleOwner) { uri ->
            println("observe currentImageUri -> $uri")
            if (uri != null) {
                println("observe currentImageUri show image -> $uri")
                showImage(uri)
            }
//            else {
//                binding.avatarImageView.setImageResource(R.drawable.profile_thumbnail_avatar_syella)
//            }
        }

        viewModel.getSession().observe(viewLifecycleOwner) { userModel ->
            currentUser = userModel
            setUserProfileDataToView(currentUser)
            println("getSession -> $userModel")
        }

        viewModel.invalidValidation.observe(viewLifecycleOwner) { message ->
            ViewComponentUtil.showToast(this.requireContext(), message)
        }
    }

    private fun setUserProfileDataToView(userModel: UserModel) {
        binding.emailTextView.text = userModel.email
        binding.nameEditText.setText(userModel.fullName)
        binding.emailEditText.setText(userModel.email)
        Glide.with(this)
            .load(userModel.profilePhoto)
            .placeholder(R.drawable.profile_thumbnail_avatar_syella)
            .into(binding.avatarImageView)
    }

    private fun setUpButtonListener() {
        binding.changePasswordCardButtonInclude.cardItemView.setOnClickListener {
            currentUser.let { userModel ->
                val bundle = Bundle()
                bundle.putParcelable(EXTRA_USER_DATA, userModel)
                findNavController().navigate(R.id.action_navigation_profile_settings_to_navigation_profile_settings_change_password, bundle)
            }
//            findNavController().navigate(R.id.action_navigation_profile_settings_to_navigation_profile_settings_change_password)
        }

        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener {
            val isValid = validateUserForm()
            if (isValid) updateProfile()
        }

        binding.editAvatarImageButton.setOnClickListener {
            startGallery()
        }
    }

    private fun validateUserForm(): Boolean {
        println("Validating User Form")
        userData = getDataUserFromForm()
        println("fullName: ${userData.first}, email: ${userData.second}, password: ${userData.third}")
        val isValid: Boolean = viewModel.validateUserForm(userData.first, userData.second, userData.third)
        println("isValid: $isValid")
        return isValid
    }

    private fun getDataUserFromForm(): Triple<String, String, String> {
        val fullName = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return Triple(fullName, email, password)
    }

    private fun updateProfile() {
        val currentImageUri = viewModel.currentImageUri.value
        println("currentImageUri -> $currentImageUri")
        currentImageUri?.let { uri ->
            val userProfileModel = getDataUser(uri)
            println("userProfileModel -> $userProfileModel")
            val userID = currentUser.userId
            println("userID -> $userID")
            if (userID != null) {
                viewModel.updateUserProfile(userID, userProfileModel).observe(viewLifecycleOwner) { resultState ->
                    if (resultState != null) {
                        when (resultState) {
                            is ResultState.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
                            }
                            is ResultState.Success -> {
//                        binding.progressBar.visibility = View.GONE
                                ViewComponentUtil.showToast(this.requireContext(), "Profile berhasil diubah")
                                updateSession(resultState.data.data)
                            }
                            is ResultState.Error -> {
//                        binding.progressBar.visibility = View.GONE
                                ViewComponentUtil.showToast(this.requireContext(), resultState.errorMessage)
                            }
                        }
                    }
                }
            } else ViewComponentUtil.showToast(this.requireContext(), "User ID tidak ditemukan")
        }
    }

    private fun updateSession(dataUserUpdated: Data) {
        val userModel = UserModel(
            dataUserUpdated.id,
            dataUserUpdated.email,
            dataUserUpdated.fullName,
            dataUserUpdated.profilePhoto,
            isLogin = true,
            isOnboardingViewed = true
        )
        viewModel.updateSession(userModel)
    }

    private fun getDataUser(avatarUri: Uri): UserProfileModel {
        val fullName = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val currentPassword = binding.passwordEditText.text.toString()
        val imageProfileFile = ImageUtil.uriToFile(avatarUri, this.requireContext()).reduceFileImage()

        val requestImageProfileFile = imageProfileFile.asRequestBody("image/*".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "profilePhoto",
            imageProfileFile.name,
            requestImageProfileFile
        )

        val requestFullName = fullName.toRequestBody("text/plain".toMediaType())
        val requestEmail = email.toRequestBody("text/plain".toMediaType())
        val requestPassword = currentPassword.toRequestBody("text/plain".toMediaType())

        return UserProfileModel(
            requestFullName,
            requestEmail,
            requestPassword,
            multipartBody
        )
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.setCurrentImageUri(uri)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }
    private fun startGallery() {
        launcherGallery.launch(
            PickVisualMediaRequest(
                ActivityResultContracts
                    .PickVisualMedia
                    .ImageOnly
            )
        )
    }

    private fun showImage(uri: Uri) {
        binding.avatarImageView.apply {
//            scaleType = ImageView.ScaleType.FIT_CENTER
//            Glide.with(this)
//                .load(userData.profilePhoto)
//                .into(binding.avatarImageView)
            setImageURI(uri)
        }
    }

    private fun setUpButtonCardItem() {
        binding.changePasswordCardButtonInclude.iconCardImageView.setImageResource(R.drawable.icon_password_edit)
        binding.changePasswordCardButtonInclude.titleItemContainerTextView.text = "Ganti Kata Sandi"
        binding.changePasswordCardButtonInclude.subTitleItemContainerTextView.text = "Buat kata sandi baru"
    }

    companion object {
        fun newInstance() = SettingsProfileFragment()
        const val EXTRA_USER_DATA = "extra_user_data"
    }
}