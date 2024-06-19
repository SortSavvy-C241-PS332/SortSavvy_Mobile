package com.bangkit.sortsavvy.data.remote.retrofit

import com.bangkit.sortsavvy.data.remote.response.LoginResponse
import com.bangkit.sortsavvy.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

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

    @Multipart
    @PUT("users/{id}")
    suspend fun updateUserProfile(
        @Path("id") id: String,
        @Part("fullName") fullName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part profilePhoto: MultipartBody.Part
    )

    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateUserPassword(
        @Path("id") id: String,
        @Field("password") currentPassword: String,
        @Field("newPassword") newPassword: String
    )
}