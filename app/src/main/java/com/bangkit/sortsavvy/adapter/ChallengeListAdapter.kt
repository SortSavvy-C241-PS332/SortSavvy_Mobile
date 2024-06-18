package com.bangkit.sortsavvy.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.ChallengeModel
import com.bangkit.sortsavvy.databinding.FragmentChallengeBinding
import com.bangkit.sortsavvy.databinding.ItemContentWithHeaderCardBinding
import com.bangkit.sortsavvy.views.main.challenge.ChallengeFragment
import com.bangkit.sortsavvy.views.main.profile.ProfileFragment

class ChallengeListAdapter(
    private val challengeModelList : List<ChallengeModel>,
    private val navController: NavController
) : RecyclerView.Adapter<ChallengeListAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemContentWithHeaderCardBinding,
        private val navController: NavController
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChallengeModel) {
            binding.apply {
                headerTextView.text = item.header
                cardItemViewInclude.itemTitleObjectTextView.text = item.title
                cardItemViewInclude.itemDescriptionObjectTextView.text = item.description
                cardItemViewInclude.thumbnailImageView.setImageResource(item.thumbnailImage)
                root.setOnClickListener {
                    ChallengeFragment.questionModelList = item.questionList
                    ChallengeFragment.titleChallenge = item.title
//                    val bundle = Bundle()
//                    bundle.putParcelable(ProfileFragment.EXTRA_USER_DATA, userModel)
                    navController.navigate(R.id.action_navigation_challenge_home_to_navigation_challenge)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemContentWithHeaderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return challengeModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(challengeModelList[position])
    }
}