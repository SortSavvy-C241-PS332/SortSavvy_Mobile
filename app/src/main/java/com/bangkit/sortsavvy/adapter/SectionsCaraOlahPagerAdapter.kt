package com.bangkit.sortsavvy.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkit.sortsavvy.data.model.LangkahItem
import com.bangkit.sortsavvy.views.main.explore.refactor.LangkahCaraOlahFragment
import com.bangkit.sortsavvy.views.main.explore.refactor.VideoCaraOlahFragment

class SectionsCaraOlahPagerAdapter(
    fragment: Fragment,
    private val langkahCaraOlahList: List<LangkahItem>,
    private val linkUrlContohCaraOlah: String,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        fragment = when (position) {
            0 -> LangkahCaraOlahFragment()
            1 -> VideoCaraOlahFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }

        val bundle = Bundle()
        bundle.putParcelableArrayList(EXTRA_LANGKAH_LIST_PARCELABLE, ArrayList(langkahCaraOlahList))
        bundle.putString(EXTRA_LINK_URL_CONTOH_CARA_OLAH, linkUrlContohCaraOlah)
        fragment.arguments = bundle

        return fragment as Fragment
    }

    companion object {
        const val EXTRA_LANGKAH_LIST_PARCELABLE = "extra_langkah_list_parcelable"
        const val EXTRA_LINK_URL_CONTOH_CARA_OLAH = "extra_link_url_contoh_cara_olah"
    }

}