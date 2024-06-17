package com.bangkit.sortsavvy.views.main.explore

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.sortsavvy.R

class JenisSampahFragment : Fragment() {

    companion object {
        fun newInstance() = JenisSampahFragment()
    }

    private val viewModel: JenisSampahViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_jenis_sampah, container, false)
    }
}