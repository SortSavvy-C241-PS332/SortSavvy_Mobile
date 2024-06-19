package com.bangkit.sortsavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ExploreJenisSampahModel
import com.bangkit.sortsavvy.databinding.ItemContentCardBinding
import com.bangkit.sortsavvy.views.main.explore.refactor.ExploreDetailSnapFragment

class ExploreJenisSampahAdapter(
    private val exploreJenisSampahList: List<ExploreJenisSampahModel>,
    private val navController: NavController
) : RecyclerView.Adapter<ExploreJenisSampahAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemContentCardBinding,
        private val navController: NavController
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExploreJenisSampahModel) {
            binding.apply {
                itemTitleObjectTextView.text = item.title
                itemDescriptionObjectTextView.text = item.description
                thumbnailImageView.setImageResource(item.exploreImage)
                root.setOnClickListener {
                    ExploreDetailSnapFragment.ciriSampahModelList = item.listCiriExploreDetailSnapModel
                    ExploreDetailSnapFragment.contohSampahModelList = item.listContohExploreDetailSnapModel
                    ExploreDetailSnapFragment.caraOlahSampahModelList = item.listCaraOlahExploreDetailSnapModel
//                    ChallengeFragment.questionModelList = item.questionList
//                    ChallengeFragment.titleChallenge = item.title
                    ExploreDetailSnapFragment.judulJenisSampah = item.title
                    ExploreDetailSnapFragment.descriptionJenisSampah = item.description
                    ExploreDetailSnapFragment.imageJenisSampah = item.exploreImage

                    println("item list ciri -> ${item.listCiriExploreDetailSnapModel}")
                    println("item list contoh -> ${item.listContohExploreDetailSnapModel}")
                    println("item list cara olah -> ${item.listCaraOlahExploreDetailSnapModel}")
                    println("=====================================")
                    println("item -> $item")

                    navController.navigate(R.id.action_navigation_explore_home_to_navigation_explore_detail_snap)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemContentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreJenisSampahAdapter.MyViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return exploreJenisSampahList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(exploreJenisSampahList[position])
    }

}