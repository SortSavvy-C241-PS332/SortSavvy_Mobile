package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.databinding.FragmentExploreDetailEducationBinding
import com.bangkit.sortsavvy.factory.ViewModelFactory

class ExploreDetailEducationFragment : Fragment() {

    private lateinit var binding: FragmentExploreDetailEducationBinding
    private lateinit var viewModel: ExploreDetailEducationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_explore_detail_education, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory= ViewModelFactory.getInstance(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ExploreDetailEducationViewModel::class.java]


    }

    companion object {
        fun newInstance() = ExploreDetailEducationFragment()
    }
}