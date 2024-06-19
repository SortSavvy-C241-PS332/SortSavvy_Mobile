package com.bangkit.sortsavvy.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LangkahItem(
    val langkahText: String,
    val langkahNumber: Int
) : Parcelable
