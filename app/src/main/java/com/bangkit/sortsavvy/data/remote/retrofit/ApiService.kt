package com.bangkit.sortsavvy.data.remote.retrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun registerAccount(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    )

    @FormUrlEncoded
    suspend fun loginAccount(
        @Field("email") email: String,
        @Field("password") password: String
    )
}