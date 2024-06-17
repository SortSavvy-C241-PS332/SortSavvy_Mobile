package com.bangkit.sortsavvy.views.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ExploreItem


class ExploreViewModel : ViewModel() {

    private val _exploreData = MutableLiveData<List<ExploreItem>>()
    val exploreData: LiveData<List<ExploreItem>> = _exploreData

    init {
        loadExploreData()
    }

    private fun loadExploreData() {
        _exploreData.value = listOf(
            ExploreItem("Apa Itu Sampah?", "Sampah adalah segala jenis bahan yang sudah tidak digunakan lagi dan dibuang oleh pemiliknya.", R.drawable.ic_launcher_background),
            ExploreItem("Sampah Organik", "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme.", R.drawable.ic_launcher_background),
            ExploreItem("Sampah Anorganik", "Sampah anorganik adalah sampah yang berasal dari bahan-bahan non-biologis dan tidak mudah terurai oleh mikroorganisme.", R.drawable.ic_launcher_background),
            ExploreItem("3R & Bank Sampah", "Sebagai anak muda, kita bisa ikut menjaga lingkungan dengan mengurangi jumlah sampah yang dibuang dan mengolah sampah menjadi barang yang bermanfaat.", R.drawable.ic_launcher_background)
        )
    }
}
