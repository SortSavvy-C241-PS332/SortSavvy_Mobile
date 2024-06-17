package com.bangkit.sortsavvy.views.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.LangkahBankSampahItem

class PenangananViewModel : ViewModel() {

    private val _langkahBankSampahData = MutableLiveData<List<LangkahBankSampahItem>>()
    val langkahBankSampahData: LiveData<List<LangkahBankSampahItem>> = _langkahBankSampahData

    init {
        loadLangkahBankSampahData()
    }

    private fun loadLangkahBankSampahData() {
        _langkahBankSampahData.value = listOf(
            LangkahBankSampahItem(1, "Bersihkan sampah anorganik dan kelompokkan sesuai jenisnya (plastik, kertas, logam, kaca)"),
            LangkahBankSampahItem(2, "Simpan sampah yang sudah dipilah dalam wadah yang terpisah."),
            LangkahBankSampahItem(3, "Bawa sampah yang sudah dipilah ke bank sampah di dekat rumahmu untuk ditimbang."),
            LangkahBankSampahItem(4, "Dapatkan poin atau uang sebagai imbalan dari sampah yang sudah ditimbang."),
            LangkahBankSampahItem(5, "Sampah yang terkumpul di Bank Sampah akan dikirim ke perusahaan untuk didaur ulang.")
        )
    }
}
