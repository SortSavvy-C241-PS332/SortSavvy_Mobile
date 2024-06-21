package com.bangkit.sortsavvy.views.main.snap

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.SessionRepository
import com.bangkit.sortsavvy.data.repository.UserStatisticRepository

class SnapResultViewModel(
    private val repository: UserStatisticRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    private val _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri> = _imageUri

    private val _classificationResults = MutableLiveData<Pair<String, Float>?>()
    val classificationResults: LiveData<Pair<String, Float>?> = _classificationResults

    fun setDataBundleSnapResult(imageUri: String?, label: String, accuracy: Float) {
        _imageUri.value = Uri.parse(imageUri)
        _classificationResults.value = Pair(label, accuracy)
    }

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }

//    fun getDataTotalScanUser(userID: Int) = repository.getDataTotalScanUser(userID)
//
//    fun updateTotalScanUser(userID: Int, wasteType: String, totalScan: Int) = repository.updateDataTotalScanUser(userID, wasteType, totalScan)

}