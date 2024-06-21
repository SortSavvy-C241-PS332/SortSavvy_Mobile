package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.LangkahCaraOlahAdapter
import com.bangkit.sortsavvy.adapter.SectionsCaraOlahPagerAdapter
import com.bangkit.sortsavvy.data.model.LangkahItem
import com.bangkit.sortsavvy.databinding.FragmentLangkahCaraOlahBinding

class LangkahCaraOlahFragment : Fragment() {

    private lateinit var binding: FragmentLangkahCaraOlahBinding
    private lateinit var langkahCaraOlahAdapter: LangkahCaraOlahAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_langkah_cara_olah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLangkahCaraOlahBinding.bind(view)

        val langkahCaraOlahList: List<LangkahItem>? = arguments?.getParcelableArrayList(SectionsCaraOlahPagerAdapter.EXTRA_LANGKAH_LIST_PARCELABLE)

        if (langkahCaraOlahList != null) {
            loadLangkahCaraOlah(langkahCaraOlahList)
        }
    }

    private fun loadLangkahCaraOlah(langkahCaraOlahList: List<LangkahItem>) {
        langkahCaraOlahAdapter = LangkahCaraOlahAdapter(langkahCaraOlahList)
        binding.langkahCaraOlahRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.langkahCaraOlahRecyclerView.adapter = langkahCaraOlahAdapter
    }

    companion object {
        fun newInstance() = LangkahCaraOlahFragment()
    }
}