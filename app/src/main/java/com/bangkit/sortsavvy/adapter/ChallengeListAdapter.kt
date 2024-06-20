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
import com.bangkit.sortsavvy.utils.ViewComponentUtil
import com.bangkit.sortsavvy.views.main.challenge.ChallengeFragment
import com.bangkit.sortsavvy.views.main.profile.ProfileFragment

class ChallengeListAdapter(
    private val challengeModelList : List<ChallengeModel>,
    private val navController: NavController,
    private val userBadge: String
) : RecyclerView.Adapter<ChallengeListAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemContentWithHeaderCardBinding,
        private val navController: NavController,
        private val userBadge: String
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChallengeModel) {
            binding.apply {
                headerTextView.text = item.header
                cardItemViewInclude.itemTitleObjectTextView.text = item.title
                cardItemViewInclude.itemDescriptionObjectTextView.text = item.description
                cardItemViewInclude.thumbnailImageView.setImageResource(item.thumbnailImage)

                setupRulesChallengeBasedOnBadge(item)

//                root.setOnClickListener {
//                    ChallengeFragment.questionModelList = item.questionList
//                    ChallengeFragment.titleChallenge = item.title
////                    val bundle = Bundle()
////                    bundle.putParcelable(ProfileFragment.EXTRA_USER_DATA, userModel)
//                    navController.navigate(R.id.action_navigation_challenge_home_to_navigation_challenge)
//                }
            }
        }

        private fun setupRulesChallengeBasedOnBadge(item: ChallengeModel) {
            println("[debug] userBadge: $userBadge")
            println("[debug] item.header: ${item.header}")
            when (userBadge) {
                "Starter" -> {
                    println("[debug] ${item.header} -> Tingkat 1: Sort Explorer")
                    if (item.header != "Tingkat 1: Sort Explorer") {
                        setupCardViewListener(item, false)
//                        binding.cardItemViewInclude.cardItemView.isEnabled = false
//                        ViewComponentUtil.showToast(binding.root.context, "Selesaikan Dulu Challenge Tingkat 1: Sort Explorer")
                        println("[debug] Starter && ${item.header} -> button card disabled")
                    } else setupCardViewListener(item, true)
                }
                "Sort Explorer" -> {
                    if (item.header != "Tingkat 1: Sort Explorer" && item.header != "Tingkat 2: Sort Warrior") {
                        setupCardViewListener(item, false)
//                        binding.cardItemViewInclude.cardItemView.isEnabled = false
//                        ViewComponentUtil.showToast(binding.root.context, "Selesaikan Dulu Challenge Tingkat 2: Sort Warrior")
                        println("[debug] Sort Explorer && ${item.header} -> button card disabled")
                    } else setupCardViewListener(item, true)
                }
                "Sort Warrior" -> {
                    if (item.header != "Tingkat 1: Sort Explorer" && item.header != "Tingkat 2: Sort Warrior" && item.header != "Tingkat 3: Sort Hero") {
                        setupCardViewListener(item, false)
//                        binding.cardItemViewInclude.cardItemView.isEnabled = false
//                        ViewComponentUtil.showToast(binding.root.context, "Selesaikan Dulu Challenge Tingkat 3: Sort Hero")
                        println("[debug] Sort Warrior && ${item.header} -> button card disabled")
                    } else setupCardViewListener(item, true)
                }
                "Sort Hero" -> {
                    if (item.header != "Tingkat 1: Sort Explorer" && item.header != "Tingkat 2: Sort Warrior" && item.header != "Tingkat 3: Sort Hero" && item.header != "Tingkat 4: Sort Savvier") {
                        setupCardViewListener(item, false)
//                        binding.cardItemViewInclude.cardItemView.isEnabled = false
//                        ViewComponentUtil.showToast(binding.root.context, "Selesaikan Dulu Challenge Tingkat 4: Sort Savvier")
                        println("[debug] Sort Hero && ${item.header} -> button card disabled")
                    } else setupCardViewListener(item, true)
                }
                "Sort Savvier" -> {
                    println("[debug] Sort Savvier && ${item.header} -> button card enable")
                    setupCardViewListener(item, true)
                }
            }
        }

        private fun setupCardViewListener(item: ChallengeModel, cardViewEnableStatus: Boolean) {
            val number = item.header.split(":")[0].split(" ")[1].toInt()
            binding.root.setOnClickListener {
                if (!cardViewEnableStatus) {
                    ViewComponentUtil.showToast(binding.root.context, "Selesaikan Dulu Challenge Tingkat ${number-1}")
                    return@setOnClickListener
                }
                setupQuestionModelList(item)
            }
        }

        private fun setupQuestionModelList(item: ChallengeModel) {
            ChallengeFragment.questionModelList = item.questionList
            ChallengeFragment.titleChallenge = item.title
//                    val bundle = Bundle()
//                    bundle.putParcelable(ProfileFragment.EXTRA_USER_DATA, userModel)
            navController.navigate(R.id.action_navigation_challenge_home_to_navigation_challenge)
        }

//        private fun setupQuestionModelList(item: ChallengeModel) {
//            println("${item.header} -> button card enabled")
//            binding.root.setOnClickListener {
//                if (cardViewEnableStatus == false) {
//                    ViewComponentUtil.showToast(binding.root.context, "Selesaikan Dulu Challenge $item.header")
//                    return@setOnClickListener
//                }
//                ChallengeFragment.questionModelList = item.questionList
//                ChallengeFragment.titleChallenge = item.title
////                    val bundle = Bundle()
////                    bundle.putParcelable(ProfileFragment.EXTRA_USER_DATA, userModel)
//                navController.navigate(R.id.action_navigation_challenge_home_to_navigation_challenge)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemContentWithHeaderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, navController, userBadge)
    }

    override fun getItemCount(): Int {
        return challengeModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(challengeModelList[position])
    }
}