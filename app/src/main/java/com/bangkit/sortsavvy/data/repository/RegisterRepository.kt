package com.bangkit.sortsavvy.data.repository

import androidx.lifecycle.liveData
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.remote.response.ErrorResponse
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class RegisterRepository(
    private val apiService: ApiService
) {

    fun registerAccount(name: String, email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.registerAccount(name, email, password)
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
        private var instance: RegisterRepository? = null
        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: RegisterRepository(apiService)
            }.also { instance = it }
    }
}