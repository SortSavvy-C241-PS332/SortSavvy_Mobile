package com.bangkit.sortsavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.OnboardingItem
import com.bangkit.sortsavvy.databinding.OnboardingItemContainerBinding

// TODO:
class OnboardingItemAdapter(private val listOnboardingItem : List<OnboardingItem>) : RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>() {

    private lateinit var binding: OnboardingItemContainerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        binding = OnboardingItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingItemViewHolder(binding)
    }

    // TODO (2): dapetin jumlah onboarding item yang ada
    override fun getItemCount(): Int {
        return listOnboardingItem.size
    }

    // TODO (4): set value berdasarkan item yang ada
    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(listOnboardingItem[position])
    }

    // TODO (3): ambil dulu itemView di onboarding item container xml
    class OnboardingItemViewHolder(private var binding: OnboardingItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onboardingItem: OnboardingItem) {
            binding.titleTextView.text = onboardingItem.title
            binding.descriptionTextView.text = onboardingItem.description
            binding.thumbnailImageView.setImageResource(onboardingItem.onboardingImage)
        }
    }
}