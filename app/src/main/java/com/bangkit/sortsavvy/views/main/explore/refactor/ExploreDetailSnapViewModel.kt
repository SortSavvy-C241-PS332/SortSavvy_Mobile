package com.bangkit.sortsavvy.views.main.explore.refactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.data.model.ExploreJenisSampahModel

class ExploreDetailSnapViewModel : ViewModel() {

    private val _exploreDetailSnap = MutableLiveData<List<ExploreJenisSampahModel>>()
    val exploreDetailSnap: LiveData<List<ExploreJenisSampahModel>> = _exploreDetailSnap

    fun loadExploreDetailSnapItem() {
//        val jenisSampahList: MutableList<ExploreJenisSampahModel> = mutableListOf()
//
//        // jenis sampah organik ciri-ciri
//        val listciriJenisSampahModel = mutableListOf<ListCiriExploreDetailJenisSampahModel>()
//        listciriJenisSampahModel.add(ListCiriExploreDetailJenisSampahModel("Mudah terurai (degradable)"))
//        listciriJenisSampahModel.add(ListCiriExploreDetailJenisSampahModel("Menghasilkan kompos yang baik untuk tanah"))
//
//        // jenis sampah organik contoh
//        val listContohJenisSampahModel = mutableListOf<ListContohExploreDetailJenisSampahModel>()
//        listContohJenisSampahModel.add(
//            ListContohExploreDetailJenisSampahModel(
//                "Sisa makanan",
//                "Kulit Buah",
//                R.drawable.challenge_thumbnail_sampah_organik,
//                "Sayuran Sisa",
//                R.drawable.ic_launcher_background
//            )
//        )
//        listContohJenisSampahModel.add(
//            ListContohExploreDetailJenisSampahModel(
//                "Sampah Tanaman",
//                "Daun Kering",
//                R.drawable.ic_launcher_background,
//                "Ranting Pohon",
//                R.drawable.ic_launcher_background
//            )
//        )
//        listContohJenisSampahModel.add(
//            ListContohExploreDetailJenisSampahModel(
//                "Sisa Dapur",
//                "Cangkang Telur",
//                R.drawable.ic_launcher_background,
//                "Ampas Kelapa",
//                R.drawable.ic_launcher_background
//            )
//        )
//        listContohJenisSampahModel.add(
//            ListContohExploreDetailJenisSampahModel(
//                "Sisa Pertanian",
//                "Kotoran hewan",
//                R.drawable.ic_launcher_background,
//                "Sisa Pakan",
//                R.drawable.ic_launcher_background
//            )
//        )
//
//        // jenis sampah organik cara olah
//        val listCaraOlahJenisSampahModel = mutableListOf<ListCaraOlahExploreDetailJenisModel>()
//        listCaraOlahJenisSampahModel.add(
//            ListCaraOlahExploreDetailJenisModel(
//                "Kompos",
//                "Sampah organik dikumpulkan dan diolah menjadi kompos melalui proses pembusukan alami.",
//                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi
//            )
//        )
//        listCaraOlahJenisSampahModel.add(
//            ListCaraOlahExploreDetailJenisModel(
//                "Eco Enzyme",
//                "Mengubah sisa makanan menjadi cairan pembersih serba guna melalui fermentasi gula dan air.",
//                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi
//            )
//        )
//
//        jenisSampahList.add(
//            ExploreJenisSampahModel(
//                "Sampah Organik",
//                "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme.",
//                R.drawable.challenge_thumbnail_sampah_organik,
//                listciriJenisSampahModel,
//                listContohJenisSampahModel,
//                listCaraOlahJenisSampahModel
//            )
//        )

//        _exploreDetailSnap.value = jenisSampahList
    }
}