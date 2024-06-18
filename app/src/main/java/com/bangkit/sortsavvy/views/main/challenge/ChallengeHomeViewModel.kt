package com.bangkit.sortsavvy.views.main.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ChallengeModel
import com.bangkit.sortsavvy.data.model.ChallengeItem

class ChallengeHomeViewModel : ViewModel() {
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

    private val _challengeModelList = MutableLiveData<List<ChallengeModel>>()
    val challengeModelList: LiveData<List<ChallengeModel>> = _challengeModelList

    fun loadChallengeModelList() {
        val challengeList: MutableList<ChallengeModel> = mutableListOf()

        challengeList.add(
            ChallengeModel("1", "Level 1: Sort Explorer", "Kenali Sampah!", "Pahami pengertian dari sampah dan kenapa kita harus peduli.", R.drawable.challenge_thumbnail_kenali_sampah)
        )
        challengeList.add(
            ChallengeModel("2", "Level 2: Sort Warrior", "Sampah Organik!", "Pahami apa itu sampah organik dan cara mengolahnya.", R.drawable.challenge_thumbnail_sampah_organik)
        )
        challengeList.add(
            ChallengeModel("3", "Level 3: Sort Hero", "Sampah Anorganik!", "Pahami apa itu sampah anorganik dan cara mengolahnya.", R.drawable.challenge_thumbnail_sampah_anorganik)
        )
        challengeList.add(
            ChallengeModel("4", "Level 4: Sort Savvier", "Aksiku untuk Bumi!", "Pahami apa itu 3R dan beragam aksi nyata untuk menjaga Bumi.", R.drawable.challenge_thumbnail_sampah_aksiku_untuk_bumi)
        )
        _challengeModelList.value = challengeList
    }
}