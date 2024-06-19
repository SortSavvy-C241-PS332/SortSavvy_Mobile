package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentExploreDetailCaraOlahBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory

class ExploreDetailCaraOlahFragment : Fragment() {

    private lateinit var binding: FragmentExploreDetailCaraOlahBinding
    private lateinit var viewModel: ExploreDetailCaraOlahViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_explore_detail_cara_olah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreDetailCaraOlahBinding.bind(view)

        val viewModelFactory = ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ExploreDetailCaraOlahViewModel::class.java]


    }

    companion object {
        fun newInstance() = ExploreDetailCaraOlahFragment()


    }

}