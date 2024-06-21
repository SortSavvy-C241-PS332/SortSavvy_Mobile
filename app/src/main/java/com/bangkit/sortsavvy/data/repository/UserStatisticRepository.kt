package com.bangkit.sortsavvy.data.repository

import androidx.lifecycle.liveData
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.remote.response.ErrorResponse
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException

class UserStatisticRepository(
    private val apiService: ApiService
) {

    fun getDataTotalScanUser(userID: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getTotalScanUser(userID)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorJsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(errorJsonString, ErrorResponse::class.java)
            emit(
                errorBody.message?.let {
                    ResultState.Error(it)
                }
            )
        }
    }

    fun updateDataTotalScanUser(userID: Int, wasteType: String, newTotalScan: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.updateTotalScanUser(userID, wasteType, newTotalScan)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorJsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(errorJsonString, ErrorResponse::class.java)
            emit(
                errorBody.message?.let {
                    ResultState.Error(it)
                }
            )
        }
    }

    companion object {
        @Volatile
        private var instance: UserStatisticRepository? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: UserStatisticRepository(apiService)
            }.also { instance = it }
    }
}