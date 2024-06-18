package com.bangkit.sortsavvy.data.model

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UserProfileModel(
    val fullName: RequestBody,
    val email: RequestBody,
    val password: RequestBody,
    val profilePhoto: MultipartBody.Part
)