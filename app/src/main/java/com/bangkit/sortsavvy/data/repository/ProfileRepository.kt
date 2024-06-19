package com.bangkit.sortsavvy.data.repository

import androidx.lifecycle.liveData
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserProfileModel
import com.bangkit.sortsavvy.data.remote.response.ErrorResponse
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.http.Part
import retrofit2.http.Path

class ProfileRepository(
    private val apiService: ApiService,
) {

    fun updateUserProfile(userID: Int, newDataUser: UserProfileModel) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.updateUserProfile(
                userID,
                newDataUser.fullName,
                newDataUser.email,
                newDataUser.password,
                newDataUser.profilePhoto
            )
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

    fun updateUserPassword(userID: String, oldPassword: String, newPassword: String) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.updateUserPassword(
                userID,
                oldPassword,
                newPassword
            )
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
        private var instance: ProfileRepository? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(apiService)
            }.also { instance = it }
    }
}