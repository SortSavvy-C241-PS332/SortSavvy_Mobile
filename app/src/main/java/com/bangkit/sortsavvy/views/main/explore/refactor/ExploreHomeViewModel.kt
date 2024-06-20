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
import com.bangkit.sortsavvy.data.model.LangkahItem


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
                R.drawable.contoh_kulit_buah,
                "Tulang Sisa",
                R.drawable.contoh_tulang
            )
        )
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sampah Tanaman",
                "Daun Kering",
                R.drawable.contoh_daun_kering,
                "Ranting Pohon",
                R.drawable.contoh_ranting
            )
        )
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa Dapur",
                "Cangkang Telur",
                R.drawable.contoh_cangkang_telur,
                "Cangkang Kelapa",
                R.drawable.contoh_cangkang_kelapa
            )
        )
        listContohJenisSampahOrganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Sisa Pertanian",
                "Kotoran hewan",
                R.drawable.contoh_kotoran_hewan,
                "Buah Busuk",
                R.drawable.contoh_buah_busuk
            )
        )

        // jenis sampah organik cara olah
        val listCaraOlahJenisSampahOrganikModel = mutableListOf<ExploreDetailCaraOlahJenisSampahModel>()
        val listLangkahItemKompos = listOf(
            LangkahItem("Kumpulkan sampah organik dalam satu wadah.", 1),
            LangkahItem("Cacah sampah organik menjadi bagian yang lebih kecil agar lebih cepat terurai.", 2),
            LangkahItem("Tambahkan lapisan tanah di atas sampah organik setiap beberapa hari.", 3),
            LangkahItem("Aduk campuran secara berkala untuk mempercepat proses pembusukan.", 4),
            LangkahItem("Tunggu beberapa minggu hingga beberapa bulan hingga sampah berubah menjadi kompos.", 5),
        )
        listCaraOlahJenisSampahOrganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Kompos",
                "Sampah organik dikumpulkan dan diolah menjadi kompos melalui proses pembusukan alami.",
                R.drawable.olah_kompos,
                listLangkahItemKompos,
                "https://www.youtube.com/embed/4WIGBvtbpOE"
            )
        )

        val listLangkahItemEcoEnzyme = listOf(
            LangkahItem("Kumpulkan sisa buah dan sayuran", 1),
            LangkahItem("Cacah dan potong menjadi bagian kecil lalu masukkan dalam sebuah wadah yang dapat ditutup (botol, galon, dsb)", 2),
            LangkahItem("Tambahkan gula dan air dengan perbandingan tertentu", 3),
            LangkahItem("Fermentasikan campuran dalam wadah tertutup selama 3 bulan", 4),
            LangkahItem("Gunakan cairan yang dihasilkan sebagai pembersih lantai, pupuk cair, atau pengusir hama.", 5),
        )
        listCaraOlahJenisSampahOrganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Eco Enzyme",
                "Mengubah sisa makanan menjadi cairan pembersih serba guna melalui fermentasi gula dan air.",
                R.drawable.olah_ecoenzyme,
                listLangkahItemEcoEnzyme,
                "https://www.youtube.com/embed/VdohdU2uOBQ"
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
                R.drawable.contoh_botol_plastik,
                "Tas Plastik",
                R.drawable.contoh_kantong_plastik
            )
        )
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Kaca",
                "Botol Kaca",
                R.drawable.contoh_gelas,
                "Cermin Rusak",
                R.drawable.contoh_cermin
            )
        )
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Kertas",
                "Koran Bekas",
                R.drawable.contoh_koran,
                "Kardus Bekas",
                R.drawable.contoh_kardus
            )
        )
        listContohJenisSampahAnorganikModel.add(
            ExploreDetailContohJenisSampahModel(
                "Logam",
                "Kaleng Minum",
                R.drawable.contoh_kaleng,
                "Sendok Rusak",
                R.drawable.contoh_sendok
            )
        )

        // jenis sampah anorganik cara olah
        val listCaraOlahJenisSampahAnorganikModel = mutableListOf<ExploreDetailCaraOlahJenisSampahModel>()

        val listLangkahItemRecycle = listOf(
            LangkahItem("Kumpulkan sampah anorganik secara terpisah", 1),
            LangkahItem("Pisahkan sampah berdasarkan jenisnya (plastik, kertas, logam, kaca)", 2),
            LangkahItem("Bersihkan sampah dari kotoran dan bahan berbahaya", 3),
            LangkahItem("Hancurkan atau mencacah sampah menjadi potongan kecil.", 4),
            LangkahItem("Olah potongan kecil menjadi bahan baku untuk produk baru (misalnya pelet plastik, lembaran kertas daur ulang).", 5)
        )
        listCaraOlahJenisSampahAnorganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Recycle (Daur Ulang)",
                "Sampah anorganik diproses kembali menjadi bahan baku untuk membuat produk baru.",
                R.drawable.olah_recycle,
                listLangkahItemRecycle,
                "https://www.youtube.com/embed/LM9nRUwOSdw"
            )
        )

        val listLangkahItemEcobrick = listOf(
            LangkahItem("Kumpulkan dan bersihkan sampah kemasan plastik dan botol plastik", 1),
            LangkahItem("Gunting sampah kemasan plastik hingga menjadi potongan kecil", 2),
            LangkahItem("Masukkan sampah plastik lembut di bawah botol kemudian lapisi dengan plastik lainnya", 3),
            LangkahItem("Padatkan plastik ke dalam botol plastik dengan tongkat dan terus lapisi hingga terisi penuh", 4),
            LangkahItem("Ecobrick sudah jadi dan siap digunakan sebagai bahan bangunan/furnitur ramah lingkungan", 5)
        )
        listCaraOlahJenisSampahAnorganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Ecobrick",
                "Mengubah botol plastik bekas yang diisi potongan plastik menjadi bata ramah lingkungan.",
                R.drawable.olah_ecobrick,
                listLangkahItemEcobrick,
                "https://www.youtube.com/embed/LPdZZpluj9c"
            )
        )

        val listLangkahItemUpcycle = listOf(
            LangkahItem("Siapkan dua botol plastik, kardus bekas, kertas karton, gunting, dan lem kertas", 1),
            LangkahItem("Potong botol plastik menjadi dua dan potong kardus membentuk lingakaran", 2),
            LangkahItem("Lapisi kardus dan dua botol plastik tadi dengan karton berwarna dan lem kertas", 3),
            LangkahItem("Tempelkan dua botol tadi ke atas kardus dengan lem kertas", 4),
            LangkahItem("Hias kardus dan botol dengan dekorasi kesukaanmu dan kotak pensil sudah jadi!", 5)
        )
        listCaraOlahJenisSampahAnorganikModel.add(
            ExploreDetailCaraOlahJenisSampahModel(
                "Upcycle (Kerajinan)",
                "Mengubah sampah anorganik menjadi produk baru dengan nilai tambah, misalnya membuat tas dari kantong plastik bekas atau dekorasi rumah dari botol kaca bekas.",
                R.drawable.olah_upcycle,
                listLangkahItemUpcycle,
                "https://www.youtube.com/embed/VdohdU2uOBQ"
            )
        )

        // add model
        jenisSampahList.add(
            ExploreJenisSampahModel(
                "Sampah Organik",
                "Sampah organik adalah sampah yang berasal dari makhluk hidup, baik itu hewan maupun tumbuhan, dan mudah terurai oleh mikroorganisme.",
                R.drawable.olah_ecobrick,
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
