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
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentSnapBinding
import com.bangkit.sortsavvy.utils.CameraUtil
import com.bangkit.sortsavvy.utils.ImageClassifierUtil

class SnapFragment : Fragment(), ImageClassifierUtil.ClassifierListener {

    private lateinit var binding: FragmentSnapBinding
    private lateinit var viewModel: SnapViewModel

    private var currentImageUri: Uri? = null

    private var imageClassifierUtil: ImageClassifierUtil? = null
    private var classificationLabel: String? = null
    private var classificationAccuracy: Float? = null

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

        imageClassifierUtil = ImageClassifierUtil(
            context = this.requireContext(),
            classifierListener = this@SnapFragment
        )
    }

    private fun setUpButtonListener() {
        binding.backBtnImageButton.setOnClickListener {
            // kalau pakai .popBackStack() bisa juga, tapi nanti ada bug ketika user ga sengaja double tap button nya
            // https://youtu.be/y2zLFONuk7c?t=122
            findNavController().navigateUp()
        }

        binding.helpBtnImageButton.setOnClickListener {
            // navigate to help fragment
            showCustomHelpDialog()
        }

        binding.galleryImageButton.setOnClickListener {
            startGallery()
        }

        binding.cameraImageButton.setOnClickListener {
            checkCameraPermissionAndStartCamera()
        }

        binding.cariButton.setOnClickListener {
            analyzeImage()
        }
    }

    private fun analyzeImage() {

        println("current image uri: $currentImageUri")

        currentImageUri?.let { imageUri ->
            val (label, accuracy) = classifyImage(imageUri)
            if (label != null && accuracy != null) {
                navigateToResultFragment(imageUri, label, accuracy)
            }
        }?:Toast.makeText(this.requireContext(), "Ambil gambar dari galeri atau kamera dulu yaa", Toast.LENGTH_SHORT).show()
    }

    private fun classifyImage(imageUri: Uri): Pair<String?, Float?> {
        imageClassifierUtil?.classifyStaticImage(imageUri)
        return Pair(classificationLabel, classificationAccuracy)
    }

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
        currentImageUri = null
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

    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isSuccess ->
        if (isSuccess) {
            startCamera()
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            showImage()
        }
    }
    private fun startCamera() {
        currentImageUri = CameraUtil.getImageUri(this.requireContext())

        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intentCamera.resolveActivity(this.requireContext().packageManager)
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, currentImageUri)
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

    private fun showImage() {
        currentImageUri?.let {  uri ->
            Log.d("image uri", "show image uri: $uri")
            binding.previewSelectedImageView.apply {
                scaleType = ImageView.ScaleType.FIT_CENTER
                setImageURI(uri)
            }
        }
    }

    companion object {
        fun newInstance() = SnapFragment()

        const val SNAP_IMAGE_URI = "EXTRA_IMAGE_URI"
        const val SNAP_RESULT = "EXTRA_SNAP_RESULT"
        const val SNAP_ACCURACY = "EXTRA_SNAP_ACCURACY"
    }

    override fun onError(error: String) {

    }

    override fun onResults(result: String, accuracy: Float) {
        classificationLabel = result
        classificationAccuracy = accuracy
    }
}