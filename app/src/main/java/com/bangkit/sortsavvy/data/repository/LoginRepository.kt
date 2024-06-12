package com.bangkit.sortsavvy.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.sortsavvy.data.model.ResultState
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.data.pref.UserPreferences
import com.bangkit.sortsavvy.data.remote.response.ErrorResponse
import com.bangkit.sortsavvy.data.remote.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class LoginRepository(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {

    fun loginAccount(email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.loginAccount(email, password)
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

    fun getSession(): Flow<UserModel> {
        return userPreferences.getSession()
    }

    suspend fun saveSession(userModel: UserModel) {
        userPreferences.saveSession(userModel)
    }

    companion object {
        @Volatile
        private var instance: LoginRepository? = null
        fun getInstance(apiService: ApiService, userPref: UserPreferences) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(apiService, userPref)
            }.also { instance = it }
    }
}