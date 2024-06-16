package com.bangkit.sortsavvy.views.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        // TODO: Use the ViewModel
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

        setUpButtonListener()
    }

    private fun setUpButtonListener() {
        binding.settingCardButtonInclude.iconCardImageView.setImageResource(R.drawable.icon_gear)
        binding.settingCardButtonInclude.titleItemContainerTextView.text = "Pengaturan Akun"
        binding.settingCardButtonInclude.subTitleItemContainerTextView.text = "Ganti nama, foto, dan kata sandi"
        binding.settingCardButtonInclude.cardItemView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_profile_settings)
        }

        binding.logoutCardButtonInclude.iconCardImageView.setImageResource(R.drawable.round_logout_24)
        binding.logoutCardButtonInclude.titleItemContainerTextView.text = "Keluar"
        binding.logoutCardButtonInclude.subTitleItemContainerTextView.text = "Keluar dari aplikasi"
        binding.logoutCardButtonInclude.cardItemView.setOnClickListener {
            Toast.makeText(this.requireContext(), "Item Card Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}