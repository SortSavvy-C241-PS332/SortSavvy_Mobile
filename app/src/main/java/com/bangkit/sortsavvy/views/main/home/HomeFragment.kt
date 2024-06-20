package com.bangkit.sortsavvy.views.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.databinding.FragmentHomeBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.main.profile.ProfileViewModel
import com.bangkit.sortsavvy.views.welcome.WelcomeActivity
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var currentUser: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        viewModel.getSession().observe(viewLifecycleOwner) { userModel ->
            if (!userModel.isLogin && userModel.isOnboardingViewed) {
                navigateToWelcomeActivity()
            } else if (userModel.isLogin && userModel.isOnboardingViewed) {
                println("userModel.isLogin -> ${userModel.isLogin} && userModel.isOnboardingViewed -> ${userModel.isOnboardingViewed}")
                currentUser = userModel
                println("userModel -> $userModel")
                println("currentUser -> $currentUser")
                setCurrentUserData(currentUser)
            }
        }

        binding.homeToSnapButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_snap)
        }
    }

    private fun setCurrentUserData(userData: UserModel) {
        binding.nameText.text = userData.fullName
        Glide.with(this)
            .load(userData.profilePhoto)
            .placeholder(R.drawable.profile_thumbnail_avatar_syella)
            .into(binding.avatarImageView)

//        userData.userId?.let {  id ->
//            getUserStatistic(id)
//        }

        println("binding data to view")
    }

    private fun getUserStatistic(userID: Int) {
        viewModel.getUserStatistic(userID).observe(viewLifecycleOwner) { resultState ->
            if (resultState != null) {
                when (resultState) {
                    is ResultState.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
//                        binding.loginButton.isEnabled = false
                    }
                    is ResultState.Success -> {
//                        binding.progressBar.visibility = View.GONE
//                        binding.loginButton.isEnabled = true
//                        println("total scan user (orgnaik dan anorganik) -> ${resultState.data.userStatistic.totalScanUser}")
//                        binding.organicCountTextView.text = resultState.data.userStatistic.totalScanUser.totalOrganik.toString()
//                        binding.anorganicCountTextView.text = resultState.data.userStatistic.totalScanUser.totalAnorganik.toString()
                    }
                    is ResultState.Error -> {
//                        binding.progressBar.visibility = View.GONE
//                        binding.loginButton.isEnabled = true
                        ViewComponentUtil.showToast(this.requireContext(), resultState.errorMessage)
                    }
                }
            }
        }
    }

    private fun navigateToWelcomeActivity() {
        val intent = Intent(requireContext(), WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}