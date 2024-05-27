package com.bangkit.sortsavvy.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.onboarding.data.OnboardingItem
import com.dicoding.sortsavvy.R

// TODO:
class OnboardingItemAdapter(private val listOnboardingItem : List<OnboardingItem>) : RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.onboarding_item_container,
            parent,
            false)
        return OnboardingItemViewHolder(view)
    }

    // TODO (2): dapetin jumlah onboarding item yang ada
    override fun getItemCount(): Int {
        return listOnboardingItem.size
    }

    // TODO (4): set value berdasarkan item yang ada
    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        val (image, title, description) = listOnboardingItem[position]
        holder.imgThumbnail.setImageResource(image)
        holder.tvTitle.text = title
        holder.tvDescription.text = description
    }

    // TODO (3): ambil dulu itemView di onboarding item container xml
    class OnboardingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumbnail: ImageView = itemView.findViewById(R.id.thumbImgv)
        val tvTitle: TextView = itemView.findViewById(R.id.titleTv)
        val tvDescription: TextView = itemView.findViewById(R.id.descriptionTv)
    }
}