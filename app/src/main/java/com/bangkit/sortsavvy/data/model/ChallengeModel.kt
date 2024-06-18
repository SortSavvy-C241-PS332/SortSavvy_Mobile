package com.bangkit.sortsavvy.data.model

data class ChallengeModel(
    val id: String,
    val header: String,
    val title: String,
    val description: String,
    val thumbnailImage: Int
) {
    constructor() : this("", "", "", "", 0)
}
