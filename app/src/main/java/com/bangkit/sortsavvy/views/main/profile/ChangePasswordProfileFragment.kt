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
import com.bangkit.sortsavvy.databinding.FragmentChangePasswordProfileBinding

class ChangePasswordProfileFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordProfileBinding
    private lateinit var viewModel: ChangePasswordProfileViewModel

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

        setUpButtonListener()
    }

    private fun setUpButtonListener() {
        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener {
            Toast.makeText(this.requireContext(), "Save Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = ChangePasswordProfileFragment()
    }
}