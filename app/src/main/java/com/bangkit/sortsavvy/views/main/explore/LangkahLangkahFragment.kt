package com.bangkit.sortsavvy.views.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.LangkahAdapter
import com.bangkit.sortsavvy.data.model.LangkahItem

class LangkahLangkahFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LangkahAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_langkah_langkah, container, false)

        recyclerView = rootView.findViewById(R.id.langkahRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = LangkahAdapter(createLangkahData())
        recyclerView.adapter = adapter

        return rootView
    }

    private fun createLangkahData(): List<LangkahItem> {
        val langkahList = mutableListOf<LangkahItem>()
        langkahList.add(LangkahItem("Kumpulkan sampah organik dalam satu wadah.", 1))
        langkahList.add(LangkahItem("Cacah sampah organik menjadi bagian yang lebih kecil agar lebih cepat terurai.", 2))
        langkahList.add(LangkahItem("Tambahkan lapisan tanah di atas sampah organik setiap beberapa hari.", 3))
        langkahList.add(LangkahItem("Aduk campuran secara berkala untuk mempercepat proses pembusukan.", 4))
        langkahList.add(LangkahItem("Tunggu beberapa minggu hingga beberapa bulan hingga sampah berubah menjadi kompos.", 5))
        return langkahList
    }

    companion object {
        fun newInstance() = LangkahLangkahFragment()
    }
}
