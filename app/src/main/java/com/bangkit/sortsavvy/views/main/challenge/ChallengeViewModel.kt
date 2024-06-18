package com.bangkit.sortsavvy.views.main.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.OnboardingItem
import com.bangkit.sortsavvy.data.model.ChallengeItem

class ChallengeViewModel : ViewModel() {
    private val _challengeItems = MutableLiveData<List<ChallengeItem>>()
    val challengeItems: LiveData<List<ChallengeItem>> = _challengeItems

     fun loadChallengeItems() {
        val items = listOf(
            ChallengeItem(
                thumbnailImage = R.drawable.challenge_thumbnail_kenali_sampah,
                title = "Kenali Sampah!",
                description = "Pahami pengertian dari sampah dan kenapa kita harus peduli."
            ),
            ChallengeItem(
                thumbnailImage = R.drawable.challenge_thumbnail_sampah_organik,
                title = "Sampah Organik!",
                description = "Pahami apa itu sampah organik dan cara mengolahnya."
            ),
            ChallengeItem(
                thumbnailImage = R.drawable.challenge_thumbnail_sampah_anorganik,
                title = "Sampah Anorganik!",
                description = "Pahami apa itu sampah anorganik dan cara mengolahnya."
            ),
            ChallengeItem(
                thumbnailImage = R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi,
                title = "Aksiku untuk Bumi!",
                description = "Pahami apa itu 3R dan beragam aksi nyata untuk menjaga Bumi."
            )
        )
        _challengeItems.value = items
    }
}