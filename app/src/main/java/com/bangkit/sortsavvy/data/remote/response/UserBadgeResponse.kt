package com.bangkit.sortsavvy.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserBadgeResponse(
    @field:SerializedName("data")
    val userBadge: UserBadge,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class UserBadge(

    @field:SerializedName("user_id")
    val userID: Int,

    @field:SerializedName("badge_user_name")
    val badgeName: String,

)