package com.bangkit.sortsavvy.data.model

data class ExploreJenisSampahModel(
    val title: String,
    val description: String,
    val exploreImage: Int,
    val listCiriExploreDetailSnapModel: List<ExploreDetailCiriJenisSampahModel>,
    val listContohExploreDetailSnapModel: List<ExploreDetailContohJenisSampahModel>,
    val listCaraOlahExploreDetailSnapModel: List<ExploreDetailCaraOlahJenisSampahModel>
)

data class ExploreDetailCiriJenisSampahModel(
    val description: String
)

data class ExploreDetailContohJenisSampahModel(
    val header: String,
    val titleA: String,
    val imageA: Int,
    val titleB: String,
    val imageB: Int
)

data class ExploreDetailCaraOlahJenisSampahModel(
    val title: String,
    val description: String,
    val image: Int,
    val listStepCaraOlah: List<LangkahItem>,
    val linkUrlContohCaraOlah: String
)