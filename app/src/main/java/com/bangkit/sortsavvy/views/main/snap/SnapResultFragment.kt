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
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentSnapResultBinding

class SnapResultFragment : Fragment() {

    private lateinit var binding: FragmentSnapResultBinding
    private lateinit var viewModel: SnapResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    private fun setUpButtonListener() {
        binding.backBtnImageButton.setOnClickListener {
            // kalau pakai .popBackStack() bisa juga, tapi nanti ada bug ketika user ga sengaja double tap button nya
            findNavController().navigateUp()
        }
        binding.scanLagiButton.setOnClickListener {

        }
        binding.resultCardInclude.cardItemView.setOnClickListener {
            Toast.makeText(this.requireContext(), "Item Card Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataBundleSnapResult() {
        if (arguments != null) {
            val imageUri = arguments?.getString(SnapFragment.SNAP_IMAGE_URI)
            val result = arguments?.getString(SnapFragment.SNAP_RESULT)
            val accuracy = arguments?.getFloat(SnapFragment.SNAP_ACCURACY)

            binding.previewSelectedImageView.setImageURI(Uri.parse(imageUri))
            binding.titleResultSnapTextView.text = result
            binding.accuracyResultSnapTextView.text = "Tingkat Keyakinan: ${accuracy}%"  // dari awal sudah dikali 100
            if (result != null) {
                setItemCard(result)
            }
        }
    }

    private fun setItemCard(result: String) {
        binding.resultCardInclude.itemTitleObjectTextView.text = result
    }

    companion object {
        fun newInstance() = SnapResultFragment()
    }
}