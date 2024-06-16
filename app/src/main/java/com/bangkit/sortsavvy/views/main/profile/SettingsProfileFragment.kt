package com.bangkit.sortsavvy.views.main.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentSettingsProfileBinding

class SettingsProfileFragment : Fragment() {

    private lateinit var binding: FragmentSettingsProfileBinding
    private lateinit var viewModel: SettingsProfileViewModel

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

        setUpButtonListener()
    }

    private fun setUpButtonListener() {
        binding.changePasswordCardButtonInclude.iconCardImageView.setImageResource(R.drawable.icon_password_edit)
        binding.changePasswordCardButtonInclude.titleItemContainerTextView.text = "Ganti Kata Sandi"
        binding.changePasswordCardButtonInclude.subTitleItemContainerTextView.text = "Buat kata sandi baru"
        binding.changePasswordCardButtonInclude.cardItemView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_settings_to_navigation_profile_settings_change_password)
        }

        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        fun newInstance() = SettingsProfileFragment()
    }
}