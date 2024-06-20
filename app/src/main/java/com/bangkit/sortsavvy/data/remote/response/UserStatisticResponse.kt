package com.bangkit.sortsavvy.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserStatisticResponse(
    @field:SerializedName("data")
    val userStatistic: UserStatistic,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class UserStatistic(

    @field:SerializedName("user_id")
    val userID: Int,

    @field:SerializedName("total_scan_user")
    val totalScanUser: TotalScanUser,

)

data class TotalScanUser(

        @field:SerializedName("organik_total")
        val totalOrganik: Int,

        @field:SerializedName("anorganik_total")
        val totalAnorganik: Int
)