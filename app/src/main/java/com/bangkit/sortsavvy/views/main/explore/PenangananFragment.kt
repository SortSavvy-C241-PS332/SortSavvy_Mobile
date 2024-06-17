package com.bangkit.sortsavvy.views.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bangkit.sortsavvy.databinding.FragmentPenangananBinding
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.LangkahBankSampahItem
import com.bangkit.sortsavvy.databinding.ItemLangkahCardBinding

class PenangananFragment : Fragment() {

    private lateinit var binding: FragmentPenangananBinding
    private val viewModel: PenangananViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPenangananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        viewModel.langkahBankSampahData.observe(viewLifecycleOwner, Observer { langkahBankSampahData ->
            if (langkahBankSampahData != null && langkahBankSampahData.size >= 5) {
                populateLangkahCard(binding.langkah1CardInclude, langkahBankSampahData[0])
                populateLangkahCard(binding.langkah2CardInclude, langkahBankSampahData[1])
                populateLangkahCard(binding.langkah3CardInclude, langkahBankSampahData[2])
                populateLangkahCard(binding.langkah4CardInclude, langkahBankSampahData[3])
                populateLangkahCard(binding.langkah5CardInclude, langkahBankSampahData[4])
            }
        })


        binding.backBtnImageButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun populateLangkahCard(cardBinding: ItemLangkahCardBinding, data: LangkahBankSampahItem) {
        cardBinding.langkahNumberTextView.text = data.langkahNumber.toString()
        cardBinding.langkahTextView.text = data.langkahText
    }


    companion object {
        fun newInstance() = PenangananFragment()
    }
}
