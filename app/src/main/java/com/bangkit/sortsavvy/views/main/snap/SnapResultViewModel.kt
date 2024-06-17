package com.bangkit.sortsavvy.views.main.snap

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SnapResultViewModel : ViewModel() {
    private val _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri> = _imageUri

    private val _classificationResults = MutableLiveData<Pair<String, Float>?>()
    val classificationResults: LiveData<Pair<String, Float>?> = _classificationResults

    fun setDataBundleSnapResult(imageUri: String?, label: String, accuracy: Float) {
        _imageUri.value = Uri.parse(imageUri)
        _classificationResults.value = Pair(label, accuracy)
    }

}