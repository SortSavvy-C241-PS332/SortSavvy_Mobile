package com.bangkit.sortsavvy.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val userDataLogin: UserDataLogin,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class UserDataLogin(

    @field:SerializedName("user_id")
    val userID: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("fullName")
    val fullName: String,

    @field:SerializedName("profilePhoto")
    val profilePhoto: String? = null,
)
