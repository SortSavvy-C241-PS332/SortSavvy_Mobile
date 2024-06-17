package com.bangkit.sortsavvy.data.model

data class UserModel(
    val userId: String,
    val email: String,
    val fullName: String,
    val profilePhoto: String? = null,
    val isLogin: Boolean = false
)