package com.bangkit.sortsavvy.views.main.explore.refactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ExploreJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailCaraOlahJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailCiriJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreDetailContohJenisSampahModel
import com.bangkit.sortsavvy.data.model.ExploreItem


class ExploreHomeViewModel : ViewModel() {

    private val _exploreJenisSampah = MutableLiveData<List<ExploreJenisSampahModel>>()
    val exploreJenisSampah: LiveData<List<ExploreJenisSampahModel>> = _exploreJenisSampah

    private val _exploreEducationItems = MutableLiveData<List<ExploreItem>>()
    val exploreEducationItems: LiveData<List<ExploreItem>> = _exploreEducationItems

//    init {
//        loadExploreData()
//    }

    fun loadExploreEducationItemList() {
        _exploreEducationItems.value = listOf(
            ExploreItem(
                "Apa Itu Sampah?",
                "Sampah adalah segala jenis bahan yang sudah tidak digunakan lagi dan dibuang oleh pemiliknya.",
                R.drawable.challenge_thumbnail_kenali_sampah
            ),
            ExploreItem(
                "3R & Bank Sampah",
                "Sebagai anak muda, kita bisa ikut menjaga lingkungan dengan mengurangi jumlah sampah yang dibuang dan mengolah sampah menjadi barang yang bermanfaat.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi
            )
        )
    }

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
        val listciriJenisSampahOrganikModel = mutableListOf<ExploreDetailCiriJenisSampahModel>()
        listciriJenisSampahOrganikModel.add(ExploreDetailCiriJenisSampahModel("Mudah terurai (degradable)"))
        listciriJenisSampahOrganikModel.add(ExploreDetailCiriJenisSampahModel("Menghasilkan kompos yang baik untuk tanah"))

        // jenis sampah organik contoh
        val listContohJenisSampahOrganikModel = mutableListOf<ExploreDetailContohJenisSampahModel>()
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa makanan",
                "Kulit Buah",
                R.drawable.challenge_thumbnail_sampah_organik,
                "Sayuran Sisa",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sampah Tanaman",
                "Daun Kering",
                R.drawable.ic_launcher_background,
                "Ranting Pohon",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa Dapur",
                "Cangkang Telur",
                R.drawable.ic_launcher_background,
                "Ampas Kelapa",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa Pertanian",
                "Kotoran hewan",
                R.drawable.ic_launcher_background,
                "Sisa Pakan",
                R.drawable.ic_launcher_background
            )
        )

        // jenis sampah organik cara olah
        val listCaraOlahJenisSampahOrganikModel = mutableListOf<ExploreDetailCaraOlahJenisSampahModel>()
        listCaraOlahJenisSampahOrganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Kompos",
                "Sampah organik dikumpulkan dan diolah menjadi kompos melalui proses pembusukan alami.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )
        listCaraOlahJenisSampahOrganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Eco Enzyme",
                "Mengubah sisa makanan menjadi cairan pembersih serba guna melalui fermentasi gula dan air.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )




        // jenis sampah anorganik
        // jenis sampah anorganik ciri-ciri
        val listciriJenisSampahAnorganikModel = mutableListOf<ExploreDetailCiriJenisSampahModel>()
        listciriJenisSampahAnorganikModel.add(ExploreDetailCiriJenisSampahModel("Tidak mudah terurai (non-degradable)"))
        listciriJenisSampahAnorganikModel.add(ExploreDetailCiriJenisSampahModel("Memerlukan bantuan teknologi untuk didaur ulang"))

        // jenis sampah anorganik contoh
        val listContohJenisSampahAnorganikModel = mutableListOf<ExploreDetailContohJenisSampahModel>()
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Plastik",
                "Botol Plastik",
                R.drawable.ic_launcher_background,
                "Tas Plastik",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Kaca",
                "Botol Kaca",
                R.drawable.ic_launcher_background,
                "Cermin Rusak",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Kertas",
                "Koran Bekas",
                R.drawable.ic_launcher_background,
                "Kardus Bekas",
                R.drawable.ic_launcher_background
            )
        )
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Logam",
                "Kaleng Minum",
                R.drawable.ic_launcher_background,
                "Sendok Rusak",
                R.drawable.ic_launcher_background
            )
        )

        // jenis sampah anorganik cara olah
        val listCaraOlahJenisSampahAnorganikModel = mutableListOf<ExploreDetailCaraOlahJenisSampahModel>()
        listCaraOlahJenisSampahAnorganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Recycle (Daur Ulang)",
                "Sampah anorganik diproses kembali menjadi bahan baku untuk membuat produk baru.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )
        listCaraOlahJenisSampahAnorganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Ecobrick",
                "Mengubah botol plastik bekas yang diisi potongan plastik menjadi bata ramah lingkungan.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )
        listCaraOlahJenisSampahAnorganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Upcycle (Kerajinan)",
                "Mengubah sampah anorganik menjadi produk baru dengan nilai tambah, misalnya membuat tas dari kantong plastik bekas atau dekorasi rumah dari botol kaca bekas.",
                R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                emptyList(),
                ""
            )
        )

        // add model
        jenisSampahList.add(
            ExploreJenisSampahModel(
                "Sampah Organik",
                "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme.",
                R.drawable.challenge_thumbnail_sampah_organik,
                listciriJenisSampahOrganikModel,
                listContohJenisSampahOrganikModel,
                listCaraOlahJenisSampahOrganikModel
            )
        )
        jenisSampahList.add(
            ExploreJenisSampahModel(
                "Sampah Anorganik",
                "Sampah anorganik adalah sampah yang berasal dari bahan-bahan non-biologis dan tidak mudah terurai oleh mikroorganisme.",
                R.drawable.challenge_thumbnail_sampah_anorganik,
                listciriJenisSampahAnorganikModel,
                listContohJenisSampahAnorganikModel,
                listCaraOlahJenisSampahAnorganikModel
            )
        )

        _exploreJenisSampah.value = jenisSampahList
    }
}
