package com.bangkit.sortsavvy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.LangkahItem

class LangkahAdapter(private val langkahList: List<LangkahItem>) : RecyclerView.Adapter<LangkahAdapter.LangkahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangkahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_langkah_card, parent, false)
        return LangkahViewHolder(view)
    }

    override fun onBindViewHolder(holder: LangkahViewHolder, position: Int) {
        val langkah = langkahList[position]
        holder.langkahNumberTextView.text = "${langkah.langkahNumber}."
        holder.langkahTextView.text = langkah.langkahText
    }

    override fun getItemCount(): Int {
        return langkahList.size
    }

    class LangkahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val langkahNumberTextView: TextView = itemView.findViewById(R.id.langkahNumberTextView)
        val langkahTextView: TextView = itemView.findViewById(R.id.langkahTextView)
    }
}
