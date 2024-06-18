package com.bangkit.sortsavvy.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: String? = null,
    val email: String? = null,
    val fullName: String? = null,
    val profilePhoto: String? = null,
    val isLogin: Boolean = false,
    val isOnboardingViewed: Boolean = false
) : Parcelable