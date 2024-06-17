package com.bangkit.sortsavvy.views.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.databinding.FragmentProfileBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.views.main.snap.SnapViewModel
import com.bangkit.sortsavvy.views.welcome.WelcomeActivity
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        setUpButtonCardItem()
        setUpButtonListener()
//        viewModel.isSessionSaved.observe(viewLifecycleOwner) {
//        }

//        viewModel.getSession().observe(viewLifecycleOwner) { userModel ->
//            println("getSession $userModel")
//            if (userModel != null) {
//                println("getSession not null $userModel")
//                getCurrentUserData(userModel)
//            } else {
//                println("getSession null")
//                findNavController().navigate(R.id.action_navigation_profile_to_navigation_welcome_activity)
//                findNavController().navigateUp()
//            }
//        }

        viewModel.getSession().observe(viewLifecycleOwner) { userModel ->
            if (!userModel.isLogin && userModel.isOnboardingViewed) {
                navigateToWelcomeActivity()
            } else if (userModel.isLogin && userModel.isOnboardingViewed) {
                getCurrentUserData(userModel)
            }
        }

//        viewModel.isLoggedOut.observe(viewLifecycleOwner) { isLoggedOut ->
//            if (isLoggedOut == true) {
//                findNavController().navigate(R.id.action_navigation_profile_to_navigation_welcome_activity)
//                println("isLoggedOut $isLoggedOut")
//                findNavController().navigateUp()
//            }
//        }
    }

    private fun navigateToWelcomeActivity() {
        val intent = Intent(requireContext(), WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun setUpButtonListener() {
        binding.settingCardButtonInclude.cardItemView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_profile_settings)
        }

        binding.logoutCardButtonInclude.cardItemView.setOnClickListener {
            viewModel.logoutSession()
        }
    }

    private fun setUpButtonCardItem() {
        binding.settingCardButtonInclude.iconCardImageView.setImageResource(R.drawable.icon_gear)
        binding.settingCardButtonInclude.titleItemContainerTextView.text = "Pengaturan Akun"
        binding.settingCardButtonInclude.subTitleItemContainerTextView.text = "Ganti nama, foto, dan kata sandi"

        binding.logoutCardButtonInclude.iconCardImageView.setImageResource(R.drawable.round_logout_24)
        binding.logoutCardButtonInclude.titleItemContainerTextView.text = "Keluar"
        binding.logoutCardButtonInclude.subTitleItemContainerTextView.text = "Keluar dari aplikasi"
    }

    private fun getCurrentUserData(userData: UserModel) {
        binding.nameTitleTextView.text = userData.fullName
        binding.emailTextView.text = userData.email
        if (userData.profilePhoto != null) {
            Glide.with(this)
                .load(userData.profilePhoto)
                .into(binding.avatarImageView)
        }

        println("binding data to view")
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}