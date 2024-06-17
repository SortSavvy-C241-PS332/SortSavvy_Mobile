package com.bangkit.sortsavvy.data.remote.retrofit

import com.bangkit.sortsavvy.data.remote.response.LoginResponse
import com.bangkit.sortsavvy.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun registerAccount(
        @Field("fullName") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginAccount(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse
}