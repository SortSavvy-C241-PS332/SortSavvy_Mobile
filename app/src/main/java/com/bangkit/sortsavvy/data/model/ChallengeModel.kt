package com.bangkit.sortsavvy.data.model

data class ChallengeModel(
    val id: String,
    val header: String,
    val title: String,
    val description: String,
    val thumbnailImage: Int,
    val questionList: List<QuestionModel>
) {
    constructor() : this("", "", "", "", 0, emptyList())
}

data class QuestionModel(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String
) {
    constructor() : this("", "", emptyList(), "")
}