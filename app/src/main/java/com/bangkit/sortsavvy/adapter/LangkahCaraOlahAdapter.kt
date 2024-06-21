package com.bangkit.sortsavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.data.model.LangkahItem
import com.bangkit.sortsavvy.databinding.ItemLangkahCardBinding

class LangkahCaraOlahAdapter(
    private val langkahCaraOlahList: List<LangkahItem>
) : RecyclerView.Adapter<LangkahCaraOlahAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemLangkahCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LangkahItem) {
            binding.langkahNumberTextView.text = item.langkahNumber.toString()
            binding.langkahTextView.text = item.langkahText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLangkahCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return langkahCaraOlahList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(langkahCaraOlahList[position])
    }
}