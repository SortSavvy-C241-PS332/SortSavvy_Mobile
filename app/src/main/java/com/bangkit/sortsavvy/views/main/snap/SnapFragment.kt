package com.bangkit.sortsavvy.views.main.snap

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentSnapBinding
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class SnapFragment : Fragment() {

    private lateinit var binding: FragmentSnapBinding
    private lateinit var viewModel: SnapViewModel

    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_snap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSnapBinding.bind(view)

        setUpButtonListener()
    }

    private fun setUpButtonListener() {
        binding.backBtnImageButton.setOnClickListener {
            // kalau pakai .popBackStack() bisa juga, tapi nanti ada bug ketika user ga sengaja double tap button nya
            // https://youtu.be/y2zLFONuk7c?t=122
            findNavController().navigateUp()
        }

        binding.helpBtnImageButton.setOnClickListener {
            // navigate to help fragment
            showCustomDialog()
        }

        binding.galleryImageButton.setOnClickListener {
            startGallery()
        }

        binding.cameraImageButton.setOnClickListener {

        }

        binding.cariButton.setOnClickListener {

        }
    }

    private fun showCustomDialog() {
        val dialogViewBuilder = AlertDialog.Builder(this.requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_layout_help_intruction, null)
        dialogViewBuilder.setView(dialogView)

        val dialog = dialogViewBuilder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.icon_background_square_rounded_white)
        // doest work set margin for dialog
//        dialog.window?.attributes?.let {
//            val marginSize = resources.getDimensionPixelSize(R.dimen.dialog_help)
//            it.width = WindowManager.LayoutParams.MATCH_PARENT - 2 * marginSize
//            it.height = WindowManager.LayoutParams.WRAP_CONTENT
//            dialog.window?.attributes = it
//            dialog.window?.setGravity(Gravity.CENTER)
//        }

        // click oke button
        dialogView.findViewById<Button>(R.id.okeButton).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
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

    private fun showImage() {
        currentImageUri?.let {  uri ->
            Log.d("image uri", "show image uri: $uri")
            binding.previewSelectedImageView.setImageURI(uri)
        }
    }

    companion object {
        fun newInstance() = SnapFragment()
    }
}