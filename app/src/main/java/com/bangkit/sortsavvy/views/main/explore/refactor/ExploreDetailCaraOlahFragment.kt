package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.SectionsCaraOlahPagerAdapter
import com.bangkit.sortsavvy.data.model.LangkahItem
import com.bangkit.sortsavvy.databinding.FragmentExploreDetailCaraOlahBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ExploreDetailCaraOlahFragment : Fragment() {

    private lateinit var binding: FragmentExploreDetailCaraOlahBinding
    private lateinit var viewModel: ExploreDetailCaraOlahViewModel

    private lateinit var sectionsPagerAdapter: SectionsCaraOlahPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_explore_detail_cara_olah, container, false)
        sectionsPagerAdapter = SectionsCaraOlahPagerAdapter(this, langkahCaraOlahList, linkUrlContohCaraOlah)
        viewPager = view.findViewById(R.id.containerCaraOlahViewPager)
        viewPager.adapter = sectionsPagerAdapter

        tabs = view.findViewById(R.id.caraOlahTabLayout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreDetailCaraOlahBinding.bind(view)

        val viewModelFactory = ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ExploreDetailCaraOlahViewModel::class.java]

        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        loadCaraOlahSampah()
    }

    private fun setupTabsLayoutAndViewPager() {

    }

    private fun loadCaraOlahSampah() {
        binding.apply {
            judulTextView.text = titleCaraOlah
            deskripsiTextView.text = descriptionCaraOlah
            caraOlahImageView.setImageResource(imageCaraOlah)

//            titleCaraOlahTextView.text = titleCaraOlah
//            descriptionCaraOlahTextView.text = descriptionCaraOlah
//            imageCaraOlahImageView.setImageResource(imageCaraOlah)
//            contohCaraOlahButton.setOnClickListener {
//                val action = ExploreDetailCaraOlahFragmentDirections.actionExploreDetailCaraOlahFragmentToExploreDetailContohFragment(linkUrlContohCaraOlah)
//                findNavController().navigate(action)
//            }
        }

//        val caraOlahSampahAdapter = ExploreDetailCaraOlahJenisSampahAdapter(langkahCaraOlahList)
//        binding.rvCaraOlahSampah.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = caraOlahSampahAdapter
//        }
    }

    companion object {
        fun newInstance() = ExploreDetailCaraOlahFragment()

        var langkahCaraOlahList: List<LangkahItem> = listOf()
        var linkUrlContohCaraOlah: String = ""

        var titleCaraOlah: String = ""
        var descriptionCaraOlah: String = ""
        var imageCaraOlah: Int = 0

        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_cara_olah_1,
            R.string.tab_text_cara_olah_2
        )
    }

}