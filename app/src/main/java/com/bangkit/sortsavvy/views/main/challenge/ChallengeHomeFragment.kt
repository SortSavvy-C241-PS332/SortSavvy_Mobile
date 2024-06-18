package com.bangkit.sortsavvy.views.main.challenge

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
import com.bangkit.sortsavvy.adapter.ChallengeListAdapter
import com.bangkit.sortsavvy.data.model.ChallengeItem
import com.bangkit.sortsavvy.data.model.ChallengeModel
import com.bangkit.sortsavvy.databinding.FragmentChallengeHomeBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory

class ChallengeHomeFragment : Fragment() {

    private lateinit var binding: FragmentChallengeHomeBinding
    private lateinit var viewModel: ChallengeHomeViewModel
    private lateinit var challengeAdapter: ChallengeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_challenge_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChallengeHomeBinding.bind(view)

        val viewModelFactory = ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ChallengeHomeViewModel::class.java]

        viewModel.loadChallengeModelList()
        viewModel.challengeModelList.observe(viewLifecycleOwner) { challengeModelList ->
            setupRecyclerView(challengeModelList)
            setButtonClickListener()
        }

//        viewModel.loadChallengeItems()
//        viewModel.challengeItems.observe(viewLifecycleOwner) { items ->
//            setUpChallengeItems(items)
//            setButtonClickListener()
//        }
    }

    private fun setupRecyclerView(challengeModelList: List<ChallengeModel>) {
        val navController = findNavController()
        challengeAdapter = ChallengeListAdapter(challengeModelList, navController)
        binding.challengeItemRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        binding.challengeItemRecyclerView.adapter = challengeAdapter
    }

    private fun setButtonClickListener() {
//        binding.sortExplorerCardInclude.cardItemView.setOnClickListener {
//            // navigate to explorer challenge
//        }
//        binding.sortWariorCardInclude.cardItemView.setOnClickListener {
//        // navigate to warrior challenge
//        }
//        binding.sortHeroCardInclude.cardItemView.setOnClickListener {
//        // navigate to hero challenge
//        }
//        binding.sortSavvierCardInclude.cardItemView.setOnClickListener {
//        // navigate to savvier challenge
//        }
    }

    private fun setUpChallengeItems(items: List<ChallengeItem>) {
//        // level 1: sort explorer
//        binding.sortExplorerCardInclude.itemTitleObjectTextView.text = items[0].title
//        binding.sortExplorerCardInclude.itemDescriptionObjectTextView.text = items[0].description
//        binding.sortExplorerCardInclude.thumbnailImageView.setImageResource(items[0].thumbnailImage)
//
//        // level 2: sort warrior
//        binding.sortWariorCardInclude.itemTitleObjectTextView.text = items[1].title
//        binding.sortWariorCardInclude.itemDescriptionObjectTextView.text = items[1].description
//        binding.sortWariorCardInclude.thumbnailImageView.setImageResource(items[1].thumbnailImage)
//
//        // level 3: sort hero
//        binding.sortHeroCardInclude.itemTitleObjectTextView.text = items[2].title
//        binding.sortHeroCardInclude.itemDescriptionObjectTextView.text = items[2].description
//        binding.sortHeroCardInclude.thumbnailImageView.setImageResource(items[2].thumbnailImage)
//
//        // level 4: sort savvier
//        binding.sortSavvierCardInclude.itemTitleObjectTextView.text = items[3].title
//        binding.sortSavvierCardInclude.itemDescriptionObjectTextView.text = items[3].description
//        binding.sortSavvierCardInclude.thumbnailImageView.setImageResource(items[3].thumbnailImage)
    }

    companion object {
        fun newInstance() = ChallengeHomeFragment()
    }
}