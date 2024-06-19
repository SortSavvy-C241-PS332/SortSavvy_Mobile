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
import com.bangkit.sortsavvy.data.model.ExploreJenisSampahModel
import com.bangkit.sortsavvy.databinding.FragmentSnapResultBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.main.explore.JenisSampahViewModel
import com.bangkit.sortsavvy.views.main.explore.refactor.ExploreDetailSnapFragment
import com.bangkit.sortsavvy.views.main.explore.refactor.ExploreDetailSnapViewModel
import com.bangkit.sortsavvy.views.main.explore.refactor.ExploreHomeViewModel

class SnapResultFragment : Fragment() {

    private lateinit var binding: FragmentSnapResultBinding
    private lateinit var viewModel: SnapResultViewModel
    private lateinit var exploreHomeViewModel: ExploreHomeViewModel

    private lateinit var result: String
    private var accuracy: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
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

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[SnapResultViewModel::class.java]

        exploreHomeViewModel = ViewModelProvider(this, viewModelFactory)[ExploreHomeViewModel::class.java]

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
            binding.resultCardInclude.cardItemView.setOnClickListener {
                exploreHomeViewModel.loadExploreJenisSampahList()
                exploreHomeViewModel.exploreJenisSampah.observe(viewLifecycleOwner) { exploreJenisSampahModel ->
                    val jenisSampah = exploreJenisSampahModel[0]
                    ExploreDetailSnapFragment.judulJenisSampah = jenisSampah.title
                    ExploreDetailSnapFragment.descriptionJenisSampah = jenisSampah.description
                    ExploreDetailSnapFragment.imageJenisSampah = jenisSampah.exploreImage

                    ExploreDetailSnapFragment.ciriSampahModelList = jenisSampah.listCiriExploreDetailSnapModel
                    ExploreDetailSnapFragment.contohSampahModelList = jenisSampah.listContohExploreDetailSnapModel
                    ExploreDetailSnapFragment.caraOlahSampahModelList = jenisSampah.listCaraOlahExploreDetailSnapModel

                    findNavController().navigate(R.id.action_navigation_snap_result_to_navigation_explore_detail_snap)
                }
            }
        } else if (result == "anorganik") {
            binding.resultCardInclude.itemDescriptionObjectTextView.text = "Sampah anorganik adalah sampah yang berasal dari bahan-bahan non-biologis dan tidak mudah terurai oleh mikroorganisme."
            binding.resultCardInclude.cardItemView.setOnClickListener {
                exploreHomeViewModel.loadExploreJenisSampahList()
                exploreHomeViewModel.exploreJenisSampah.observe(viewLifecycleOwner) { exploreJenisSampahModel ->
                    val jenisSampah = exploreJenisSampahModel[1]
                    ExploreDetailSnapFragment.judulJenisSampah = jenisSampah.title
                    ExploreDetailSnapFragment.descriptionJenisSampah = jenisSampah.description
                    ExploreDetailSnapFragment.imageJenisSampah = jenisSampah.exploreImage

                    ExploreDetailSnapFragment.ciriSampahModelList = jenisSampah.listCiriExploreDetailSnapModel
                    ExploreDetailSnapFragment.contohSampahModelList = jenisSampah.listContohExploreDetailSnapModel
                    ExploreDetailSnapFragment.caraOlahSampahModelList = jenisSampah.listCaraOlahExploreDetailSnapModel

                    findNavController().navigate(R.id.action_navigation_snap_result_to_navigation_explore_detail_snap)
                }
            }
        }
    }

    companion object {
        fun newInstance() = SnapResultFragment()
    }
}