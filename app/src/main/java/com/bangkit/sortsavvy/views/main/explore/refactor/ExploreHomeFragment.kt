package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.ExploreJenisSampahAdapter
import com.bangkit.sortsavvy.data.model.ExploreItem
import com.bangkit.sortsavvy.data.model.ExploreJenisSampahModel
import com.bangkit.sortsavvy.databinding.FragmentExploreHomeBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class ExploreHomeFragment : Fragment() {

    private lateinit var binding: FragmentExploreHomeBinding
    private lateinit var viewModel: ExploreHomeViewModel

    private lateinit var exploreJenisSampahAdapter: ExploreJenisSampahAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_explore_home, container, false)

//        viewModel.exploreData.observe(viewLifecycleOwner, Observer { exploreData ->
//            if (exploreData != null && exploreData.size == 4) {
//                // Pengenalan Card
//                val pengenalanCardView = view.findViewById<View>(R.id.pengenalanCardInclude)
//                val pengenalanTitle = pengenalanCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
//                val pengenalanDescription = pengenalanCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
//                val pengenalanImage = pengenalanCardView.findViewById<ImageView>(R.id.thumbnailImageView)
//                pengenalanTitle.text = exploreData[0].title
//                pengenalanDescription.text = exploreData[0].description
//                pengenalanImage.setImageResource(exploreData[0].exploreImage)
//
//                // Organik Card
//                val organikCardView = view.findViewById<View>(R.id.organikCardInclude)
//                val organikTitle = organikCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
//                val organikDescription = organikCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
//                val organikImage = organikCardView.findViewById<ImageView>(R.id.thumbnailImageView)
//                organikTitle.text = exploreData[1].title
//                organikDescription.text = exploreData[1].description
//                organikImage.setImageResource(exploreData[1].exploreImage)
//
//                // Anorganik Card
//                val anorganikCardView = view.findViewById<View>(R.id.anorganikCardInclude)
//                val anorganikTitle = anorganikCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
//                val anorganikDescription = anorganikCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
//                val anorganikImage = anorganikCardView.findViewById<ImageView>(R.id.thumbnailImageView)
//                anorganikTitle.text = exploreData[2].title
//                anorganikDescription.text = exploreData[2].description
//                anorganikImage.setImageResource(exploreData[2].exploreImage)
//
//                // Penanganan Card
//                val penangananCardView = view.findViewById<View>(R.id.penangananCardInclude)
//                val penangananTitle = penangananCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
//                val penangananDescription = penangananCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
//                val penangananImage = penangananCardView.findViewById<ImageView>(R.id.thumbnailImageView)
//                penangananTitle.text = exploreData[3].title
//                penangananDescription.text = exploreData[3].description
//                penangananImage.setImageResource(exploreData[3].exploreImage)
//            }
//        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreHomeBinding.bind(view)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ExploreHomeViewModel::class.java]

        viewModel.loadExploreJenisSampahList()
        viewModel.exploreJenisSampah.observe(viewLifecycleOwner) { exploreJenisSampahItems ->
//            setupExploreItemToView(exploreItems)
//            setupButtonClickListener()
            setupRecyclerView(exploreJenisSampahItems)
        }

        viewModel.loadExploreEducationItemList()
        viewModel.exploreEducationItems.observe(viewLifecycleOwner) { exploreEducationItems ->
            setupExploreItemToView(exploreEducationItems)
            setupButtonClickListener()
        }
    }

    private fun setupRecyclerView(exploreItemList: List<ExploreJenisSampahModel>) {
        val navController = findNavController()
        exploreJenisSampahAdapter = ExploreJenisSampahAdapter(exploreItemList, navController)
        binding.jenisSampahItemRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        binding.jenisSampahItemRecyclerView.adapter = exploreJenisSampahAdapter
    }

    private fun setupButtonClickListener() {
        // pengenalan card
        binding.pengenalanCardInclude.cardItemView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_explore_home_to_navigation_explore_pengenalan_sampah)
        }
//        // organik card
//        binding.organikCardInclude.cardItemView.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(EXTRA_EXPLORE_ID_ITEM, "1")
//            findNavController().navigate(R.id.action_navigation_explore_home_to_navigation_explore_detail_snap, bundle)
//        }
//        // anorganik card
//        binding.anorganikCardInclude.cardItemView.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(EXTRA_EXPLORE_ID_ITEM, "2")
//            findNavController().navigate(R.id.action_navigation_explore_home_to_navigation_explore_detail_snap, bundle)
//        }
        // penanganan card
        binding.penangananCardInclude.cardItemView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_explore_home_to_navigation_explore_penanganan)
        }
    }

    private fun setupExploreItemToView(exploreItems: List<ExploreItem>) {
        // Pengenalan Card
        binding.pengenalanCardInclude.itemTitleObjectTextView.text = exploreItems[0].title
        binding.pengenalanCardInclude.itemDescriptionObjectTextView.text = exploreItems[0].description
        binding.pengenalanCardInclude.thumbnailImageView.setImageResource(exploreItems[0].exploreImage)
//
//        // Organik Card
//        binding.organikCardInclude.itemTitleObjectTextView.text = exploreItems[1].title
//        binding.organikCardInclude.itemDescriptionObjectTextView.text = exploreItems[1].description
//        binding.organikCardInclude.thumbnailImageView.setImageResource(exploreItems[1].exploreImage)
//
//        // Anorganik Card
//        binding.anorganikCardInclude.itemTitleObjectTextView.text = exploreItems[2].title
//        binding.anorganikCardInclude.itemDescriptionObjectTextView.text = exploreItems[2].description
//        binding.anorganikCardInclude.thumbnailImageView.setImageResource(exploreItems[2].exploreImage)
//
        // Penanganan Card
        binding.penangananCardInclude.itemTitleObjectTextView.text = exploreItems[1].title
        binding.penangananCardInclude.itemDescriptionObjectTextView.text = exploreItems[1].description
        binding.penangananCardInclude.thumbnailImageView.setImageResource(exploreItems[1].exploreImage)

    }

    companion object {
        fun newInstance() = ExploreHomeFragment()
        const val EXTRA_EXPLORE_ID_ITEM = "extra_explore_id_item"
    }
}
