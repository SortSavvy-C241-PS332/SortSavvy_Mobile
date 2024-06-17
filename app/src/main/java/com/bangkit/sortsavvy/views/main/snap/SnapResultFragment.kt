package com.bangkit.sortsavvy.views.main.snap

import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentSnapResultBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class SnapResultFragment : Fragment() {

    private lateinit var binding: FragmentSnapResultBinding
    private lateinit var viewModel: SnapResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[SnapResultViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_snap_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSnapResultBinding.bind(view)

        setUpButtonListener()
        getDataBundleSnapResult()

        viewModel.imageUri.observe(viewLifecycleOwner) { uri ->
            binding.previewSelectedImageView.setImageURI(uri)
        }

        viewModel.classificationResults.observe(viewLifecycleOwner) { result ->
            binding.titleResultSnapTextView.text = result?.first
            binding.accuracyResultSnapTextView.text = "Tingkat Keyakinan: " + String.format("%.2f", result?.second)
        }
    }

    private fun setUpButtonListener() {
        binding.scanLagiButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.resultCardInclude.cardItemView.setOnClickListener {
            ViewComponentUtil.showToast(this.requireContext(), "Item Card Clicked")
        }
    }

    private fun getDataBundleSnapResult() {
        if (arguments != null) {
            val imageUri = arguments?.getString(SnapFragment.SNAP_IMAGE_URI)
            val result = arguments?.getString(SnapFragment.SNAP_RESULT)
            val accuracy = arguments?.getFloat(SnapFragment.SNAP_ACCURACY)

            if (result != null && accuracy != null && imageUri != null) {
                viewModel.setDataBundleSnapResult(imageUri, result, accuracy)
                setItemCard(result)
            }

        }
    }

    private fun setItemCard(result: String) {
        binding.resultCardInclude.itemTitleObjectTextView.text = result
        if (result == "organik") {
            binding.resultCardInclude.itemDescriptionObjectTextView.text = "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme."
        } else if (result == "anorganik") {
            binding.resultCardInclude.itemDescriptionObjectTextView.text = "Sampah anorganik adalah sampah yang berasal dari bahan-bahan non-biologis dan tidak mudah terurai oleh mikroorganisme."
        }
    }

    companion object {
        fun newInstance() = SnapResultFragment()
    }
}