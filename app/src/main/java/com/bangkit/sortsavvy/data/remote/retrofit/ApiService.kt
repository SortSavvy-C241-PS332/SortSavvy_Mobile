package com.bangkit.sortsavvy.data.remote.retrofit

import com.bangkit.sortsavvy.data.remote.response.LoginResponse
import com.bangkit.sortsavvy.data.remote.response.RegisterResponse
import com.bangkit.sortsavvy.data.remote.response.UpdateProfileResponse
import com.bangkit.sortsavvy.data.remote.response.UserBadgeResponse
import com.bangkit.sortsavvy.data.remote.response.UserStatisticResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    // register account
    @FormUrlEncoded
    @POST("register")
    suspend fun registerAccount(
        @Field("fullName") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ) : RegisterResponse

    // login account
    @FormUrlEncoded
    @POST("login")
    suspend fun loginAccount(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    // update user profile
    @Multipart
    @PUT("users/{id}")
    suspend fun updateUserProfile(
        @Path("id") id: Int,
        @Part("fullName") fullName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part profilePhoto: MultipartBody.Part
    ) : UpdateProfileResponse

    // update user password
    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateUserPassword(
        @Path("id") id: Int,
        @Field("password") currentPassword: String,
        @Field("newPassword") newPassword: String
    )

    // get total scan user
//    @FormUrlEncoded
    @GET("users/{id}")
    suspend fun getTotalScanUser(
        @Path("id") id: Int
    ) : UserStatisticResponse

    // get total scan user
    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateTotalScanUser(
        @Path("id") id: Int,
        @Field("jenisSampah") wasteType: String,
        @Field("totalScan") totalScan: Int
    ) : UserStatisticResponse

    // get badge user
//    @FormUrlEncoded
    @GET("users/{id}")
    suspend fun getBadgeUser(
        @Path("id") id: Int
    ) : UserBadgeResponse

    // update badge user
    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateBadgeUser(
        @Path("id") id: Int,
        @Field("badgeType") badgeType: String
    ) : UserBadgeResponse

}