package com.bangkit.sortsavvy.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdatePasswordResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)