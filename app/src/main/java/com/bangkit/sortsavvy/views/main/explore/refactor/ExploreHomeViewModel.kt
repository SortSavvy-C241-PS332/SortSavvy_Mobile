package com.bangkit.sortsavvy.views.main.explore.refactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ExploreJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailCaraOlahJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailCiriJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailContohJenisSampahModel


class ExploreHomeViewModel : ViewModel() {

    private val _exploreJenisSampah = MutableLiveData<List<ExploreJenisSampahModel>>()
    val exploreJenisSampah: LiveData<List<ExploreJenisSampahModel>> = _exploreJenisSampah

//    init {
//        loadExploreData()
//    }

    fun loadExploreJenisSampahList() {
//        _exploreJenisSampah.value = listOf(
//            ExploreItem(
//                "Apa Itu Sampah?",
//                "Sampah adalah segala jenis bahan yang sudah tidak digunakan lagi dan dibuang oleh pemiliknya.",
//                R.drawable.challenge_thumbnail_kenali_sampah
//            ),
//            ExploreJenisSampahModel(
//                "Sampah Organik",
//                "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme.",
//                R.drawable.challenge_thumbnail_sampah_organik
//            ),
//            ExploreJenisSampahModel(
//                "Sampah Anorganik",
//                "Sampah anorganik adalah sampah yang berasal dari bahan-bahan non-biologis dan tidak mudah terurai oleh mikroorganisme.",
//                R.drawable.challenge_thumbnail_sampah_anorganik
//            ),
//            ExploreItem(
//                "3R & Bank Sampah",
//                "Sebagai anak muda, kita bisa ikut menjaga lingkungan dengan mengurangi jumlah sampah yang dibuang dan mengolah sampah menjadi barang yang bermanfaat.",
//                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi
//            )
//        )

        val jenisSampahList: MutableList<ExploreJenisSampahModel> = mutableListOf()

        // jenis sampah organik ciri-ciri
        val listciriJenisSampahModel = mutableListOf<ExploreDetailCiriJenisSampahModel>()
        listciriJenisSampahModel.add(ExploreDetailCiriJenisSampahModel("Mudah terurai (degradable)"))
        listciriJenisSampahModel.add(ExploreDetailCiriJenisSampahModel("Menghasilkan kompos yang baik untuk tanah"))

        // jenis sampah organik contoh
        val listContohJenisSampahModel = mutableListOf<ExploreDetailContohJenisSampahModel>()
        listContohJenisSampahModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa makanan",
                "Kulit Buah",
                R.drawable.challenge_thumbnail_sampah_organik,
                "Sayuran Sisa",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sampah Tanaman",
                "Daun Kering",
                R.drawable.ic_launcher_background,
                "Ranting Pohon",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa Dapur",
                "Cangkang Telur",
                R.drawable.ic_launcher_background,
                "Ampas Kelapa",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa Pertanian",
                "Kotoran hewan",
                R.drawable.ic_launcher_background,
                "Sisa Pakan",
                R.drawable.ic_launcher_background
            )
        )

        // jenis sampah organik cara olah
        val listCaraOlahJenisSampahModel = mutableListOf<ExploreDetailCaraOlahJenisSampahModel>()
        listCaraOlahJenisSampahModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Kompos",
                "Sampah organik dikumpulkan dan diolah menjadi kompos melalui proses pembusukan alami.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )
        listCaraOlahJenisSampahModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Eco Enzyme",
                "Mengubah sisa makanan menjadi cairan pembersih serba guna melalui fermentasi gula dan air.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )

        jenisSampahList.add(
            ExploreJenisSampahModel(
                "Sampah Organik",
                "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme.",
                R.drawable.challenge_thumbnail_sampah_organik,
                listciriJenisSampahModel,
                listContohJenisSampahModel,
                listCaraOlahJenisSampahModel
            )
        )

        _exploreJenisSampah.value = jenisSampahList
    }
}
