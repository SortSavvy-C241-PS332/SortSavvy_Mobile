package com.bangkit.sortsavvy.views.main.snap

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.repository.SessionRepository
import com.bangkit.sortsavvy.data.repository.UserStatisticRepository
import com.bangkit.sortsavvy.utils.ImageClassifierUtil

class SnapViewModel(
    private val sessionRepository: SessionRepository,
    private val userStatisticRepository: UserStatisticRepository
) : ViewModel(), ImageClassifierUtil.ClassifierListener {
    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> = _currentImageUri

    private val _tempImageUri = MutableLiveData<Uri?>()
    val tempImageUri: LiveData<Uri?> = _tempImageUri

    private val _classificationResults = MutableLiveData<Pair<String, Float>?>()
    val classificationResults: LiveData<Pair<String, Float>?> = _classificationResults

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun setCurrentImageUri(uri: Uri?) {
        _currentImageUri.postValue(uri)
    }

    fun setTempImageUri(uri: Uri?) {
        _tempImageUri.value = uri
    }

    fun analyzeImage(imageUri: Uri, imageClassifierUtil: ImageClassifierUtil) {
        imageClassifierUtil.classifyStaticImage(imageUri)
    }

    private fun setClassificationResults(label: String, accuracy: Float) {
        _classificationResults.value = Pair(label, accuracy)
    }

    private fun setError(errorMessage: String) {
        _error.value = errorMessage
    }

    override fun onError(error: String) {
        setError(error)
    }

    override fun onResults(result: String, accuracy: Float) {
        println("uri Result: $result, Accuracy: $accuracy")
        setClassificationResults(result, accuracy)
    }

    fun getSession(): LiveData<UserModel> {
        return sessionRepository.getSession().asLiveData()
    }

    fun getDataTotalScanUser(userID: Int) = userStatisticRepository.getDataTotalScanUser(userID)

    fun updateTotalScanUser(userID: Int, wasteType: String, totalScan: Int) = userStatisticRepository.updateDataTotalScanUser(userID, wasteType, totalScan)
}