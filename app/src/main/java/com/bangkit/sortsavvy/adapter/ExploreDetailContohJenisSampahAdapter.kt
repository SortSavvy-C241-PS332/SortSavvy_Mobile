package com.bangkit.sortsavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.data.model.ExploreDetailContohJenisSampahModel
import com.bangkit.sortsavvy.databinding.ItemExploreDetailContohSnapBinding

class ExploreDetailContohJenisSampahAdapter(
    private val exploreDetailContohJenisSampahList: List<ExploreDetailContohJenisSampahModel>
) : RecyclerView.Adapter<ExploreDetailContohJenisSampahAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemExploreDetailContohSnapBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExploreDetailContohJenisSampahModel) {
            binding.apply {
                titleContohItemTextView.text = item.header

                titleItemContohATextView.text = item.titleA
                thumbnailAContohItemImageView.setImageResource(item.imageA)
                titleItemContohBTextView.text = item.titleB
                thumbnailBContohItemImageView.setImageResource(item.imageB)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemExploreDetailContohSnapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return exploreDetailContohJenisSampahList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(exploreDetailContohJenisSampahList[position])
    }

}