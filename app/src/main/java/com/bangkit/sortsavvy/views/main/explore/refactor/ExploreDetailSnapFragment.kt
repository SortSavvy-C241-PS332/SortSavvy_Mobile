package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.ExploreDetailCaraOlahJenisSampahAdapter
import com.bangkit.sortsavvy.adapter.ExploreDetailCiriJenisSampahAdapter
import com.bangkit.sortsavvy.adapter.ExploreDetailContohJenisSampahAdapter
import com.bangkit.sortsavvy.data.model.ExploreDetailCaraOlahJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailCiriJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailContohJenisSampahModel
import com.bangkit.sortsavvy.databinding.FragmentExploreDetailSnapBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory

class ExploreDetailSnapFragment : Fragment() {

    private lateinit var binding: FragmentExploreDetailSnapBinding
    private lateinit var viewModel: ExploreDetailSnapViewModel

    private lateinit var exploreDetailCiriJenisSampahAdapter: ExploreDetailCiriJenisSampahAdapter
    private lateinit var exploreDetailContohJenisSampahAdapter: ExploreDetailContohJenisSampahAdapter
    private lateinit var exploreDetailCaraOlahJenisSampahAdapter: ExploreDetailCaraOlahJenisSampahAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_explore_detail_snap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreDetailSnapBinding.bind(view)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ExploreDetailSnapViewModel::class.java]

//        if (arguments != null) {
//            val exploreID = arguments?.getString(ExploreHomeFragment.EXTRA_EXPLORE_ID_ITEM)
//
//            if (exploreID != null) {
//                viewModel.setDataBundleSnapResult(imageUri, result, accuracy)
//                setItemCard(result)
//            }
//
//        }

        binding.apply {
            backBtnImageButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        loadJenisSampahData()
    }

    private fun loadJenisSampahData() {
        binding.apply {
            judulTextView.text = judulJenisSampah
            deskripsiTextView.text = descriptionJenisSampah
            jenisSampahImageView.setImageResource(imageJenisSampah)
        }

        setupRecyclerView(ciriSampahModelList, contohSampahModelList, caraOlahSampahModelList)
    }

    private fun setupRecyclerView(
        listCiri: List<ExploreDetailCiriJenisSampahModel>,
        listContoh: List<ExploreDetailContohJenisSampahModel>,
        listCaraOlah: List<ExploreDetailCaraOlahJenisSampahModel>
    ) {
        exploreDetailCiriJenisSampahAdapter = ExploreDetailCiriJenisSampahAdapter(listCiri)
        binding.ciriRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.ciriRecyclerView.adapter = exploreDetailCiriJenisSampahAdapter

        exploreDetailContohJenisSampahAdapter = ExploreDetailContohJenisSampahAdapter(listContoh)
        binding.contohRecyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)
        binding.contohRecyclerView.adapter = exploreDetailContohJenisSampahAdapter

        val navController = findNavController()
        exploreDetailCaraOlahJenisSampahAdapter = ExploreDetailCaraOlahJenisSampahAdapter(listCaraOlah, navController)
        binding.caraOlahRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.caraOlahRecyclerView.adapter = exploreDetailCaraOlahJenisSampahAdapter
    }

    companion object {
        fun newInstance() = ExploreDetailSnapFragment()

        var ciriSampahModelList: List<ExploreDetailCiriJenisSampahModel> = listOf()
        var contohSampahModelList: List<ExploreDetailContohJenisSampahModel> = listOf()
        var caraOlahSampahModelList: List<ExploreDetailCaraOlahJenisSampahModel> = listOf()

        var judulJenisSampah: String = ""
        var descriptionJenisSampah: String = ""
        var imageJenisSampah: Int = 0
    }
}