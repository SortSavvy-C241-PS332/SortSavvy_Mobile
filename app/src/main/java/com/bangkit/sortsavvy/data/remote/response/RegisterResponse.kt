package com.bangkit.sortsavvy.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("data")
    val userDataRegister: UserDataRegister,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class UserDataRegister(

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("profilePhoto")
    val profilePhoto: Any? = null,
)