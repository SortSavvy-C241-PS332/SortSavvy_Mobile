package com.bangkit.sortsavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.data.model.ExploreDetailCiriJenisSampahModel
import com.bangkit.sortsavvy.databinding.ItemCiriCardBinding

class ExploreDetailCiriJenisSampahAdapter(
    private val exploreDetailCiriJenisSampahList: List<ExploreDetailCiriJenisSampahModel>
) : RecyclerView.Adapter<ExploreDetailCiriJenisSampahAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemCiriCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExploreDetailCiriJenisSampahModel) {
            binding.itemTitleObject1TextView.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCiriCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return exploreDetailCiriJenisSampahList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(exploreDetailCiriJenisSampahList[position])
    }
}