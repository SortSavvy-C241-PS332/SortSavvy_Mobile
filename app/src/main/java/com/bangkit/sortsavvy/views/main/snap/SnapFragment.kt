package com.bangkit.sortsavvy.views.main.snap

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentSnapBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.CameraUtil
import com.bangkit.sortsavvy.utils.ImageClassifierUtil
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.authentication.login.LoginViewModel

class SnapFragment : Fragment() {

    private lateinit var binding: FragmentSnapBinding
    private lateinit var viewModel: SnapViewModel

//    private var currentImageUri: Uri? = null

    private var imageClassifierUtil: ImageClassifierUtil? = null
//    private var classificationLabel: String? = null
//    private var classificationAccuracy: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[SnapViewModel::class.java]

        setUpButtonListener()

        imageClassifierUtil = ImageClassifierUtil(
            context = this.requireContext(),
            classifierListener = viewModel
        )

        viewModel.currentImageUri.observe(viewLifecycleOwner) { uri ->
            println("observe currentImageUri -> $uri")
            if (uri != null) {
                println("observe currentImageUri show image -> $uri")
                showImage(uri)
            } else {
                binding.previewSelectedImageView.setImageResource(R.drawable.ic_background_outline_image_64)
            }
        }

        viewModel.classificationResults.observe(viewLifecycleOwner) { result ->
            println("observe classificationResults -> ${result?.first} - ${result?.second}")
            result?.let { (label, accuracy) ->
                viewModel.currentImageUri.value?.let { uri ->
                    println("observe classificationResults -> $uri, $label, $accuracy")
                    navigateToResultFragment(uri, label, accuracy)
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            println("observe error -> $error")
            error?.let { message ->
                ViewComponentUtil.showToast(this.requireContext(), message)
            }
        }
    }

    private fun setUpButtonListener() {
        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.helpBtnImageButton.setOnClickListener {
            showCustomHelpDialog()
        }

        binding.galleryImageButton.setOnClickListener {
            startGallery()
        }

        binding.cameraImageButton.setOnClickListener {
            checkCameraPermissionAndStartCamera()
        }

        binding.cariButton.setOnClickListener {
            viewModel.currentImageUri.value?.let { uri ->
                println("cariButton currentImageUri -> $uri")
                imageClassifierUtil?.let { util ->
                    println("cariButton imageClassifierUtil -> $uri")
                    viewModel.analyzeImage(uri, util)
                }
            } ?: ViewComponentUtil.showToast(this.requireContext(), "Ambil gambar dari galeri atau kamera dulu yaa")
        }
    }

//    private fun analyzeImage() {
//
//        println("current image uri: $currentImageUri")
//
//        currentImageUri?.let { imageUri ->
//            val (label, accuracy) = classifyImage(imageUri)
//            if (label != null && accuracy != null) {
//                navigateToResultFragment(imageUri, label, accuracy)
//            }
//        }?: ViewComponentUtil.showToast(this.requireContext(), "Ambil gambar dari galeri atau kamera dulu yaa")
//    }

//    private fun classifyImage(imageUri: Uri): Pair<String?, Float?> {
//        imageClassifierUtil?.classifyStaticImage(imageUri)
//        return Pair(classificationLabel, classificationAccuracy)
//    }

    private fun navigateToResultFragment(imageUri: Uri, result: String, accuracy: Float) {
        val bundleData = Bundle().apply {
            putString(SNAP_IMAGE_URI, imageUri.toString())
            putString(SNAP_RESULT, result)
            putFloat(SNAP_ACCURACY, accuracy)
        }

        findNavController().navigate(R.id.action_snapFragment_to_snapResultFragment, bundleData)
//        snapResultFragment.arguments = bundleData
//
//        val fragmentManager = parentFragmentManager
//        fragmentManager.commit {
//            addToBackStack(null)
//            add(R.id.nav_host_fragment_activity_main, snapResultFragment, SnapResultFragment::class.java.simpleName)
//        }
        viewModel.setCurrentImageUri(null)
    }

    private fun showCustomHelpDialog() {
        val dialogViewBuilder = AlertDialog.Builder(this.requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_layout_help_intruction, null)
        dialogViewBuilder.setView(dialogView)

        val dialog = dialogViewBuilder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.icon_background_square_rounded_white)

        // click oke button
        dialogView.findViewById<Button>(R.id.okeButton).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showCustomCameraRequestDialog(permission: String? = null) {
        val dialogViewBuilder = AlertDialog.Builder(this.requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_layout_request_access_camera, null)
        dialogViewBuilder.setView(dialogView)

        val dialog = dialogViewBuilder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.icon_background_square_rounded_dark)

        // click oke button
        dialogView.findViewById<Button>(R.id.allowButton).setOnClickListener {
            permission?.let {  access ->
                requestCameraPermission.launch(access)
            }
            dialog.dismiss()
        }

        dialog.show()
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

    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isSuccess ->
        if (isSuccess) {
            println("requestCameraPermission start camera")
            startCamera()
        } else {
            viewModel.currentImageUri.value?.let { uri ->
                showImage(uri)
            } ?: viewModel.setCurrentImageUri(null)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            println("launcherIntentCamera currentUri -> ${viewModel.currentImageUri.value}")
            val getTempUri = viewModel.tempImageUri.value
            println("launcherIntentCamera tempUri -> $getTempUri")
            viewModel.setCurrentImageUri(getTempUri)
            println("launcherIntentCamera currentUri after setobserve -> ${viewModel.currentImageUri.value}")

//            viewModel.currentImageUri.value?.let { uri ->
//                println("launcherIntentCamera RESULT_OK uri -> $uri")
//                showImage(uri)
//            }
        } else {
            println("launcherIntentCamera uri else -> ${viewModel.currentImageUri.value}")
            viewModel.currentImageUri.value?.let { uri ->
                showImage(uri)
            } ?: viewModel.setCurrentImageUri(null)
        }
    }

    private fun startCamera() {
        val uri = CameraUtil.getImageUri(this.requireContext())
        viewModel.setTempImageUri(uri)

        println("startCamera uri -> $uri")

        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intentCamera.resolveActivity(this.requireContext().packageManager)
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        launcherIntentCamera.launch(intentCamera)
    }

    private fun checkCameraPermissionAndStartCamera() {
        val permission = Manifest.permission.CAMERA
        when {
            ContextCompat.checkSelfPermission(this.requireContext(), permission) == PackageManager.PERMISSION_GRANTED -> {
                startCamera()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                showCustomCameraRequestDialog(permission)
            }
            else -> {
                showCustomCameraRequestDialog(permission)
            }
        }
    }

    private fun showImage(uri: Uri) {
        binding.previewSelectedImageView.apply {
            scaleType = ImageView.ScaleType.FIT_CENTER
            setImageURI(uri)
        }
    }

    companion object {
        fun newInstance() = SnapFragment()

        const val SNAP_IMAGE_URI = "EXTRA_IMAGE_URI"
        const val SNAP_RESULT = "EXTRA_SNAP_RESULT"
        const val SNAP_ACCURACY = "EXTRA_SNAP_ACCURACY"
    }
}