package com.bangkit.sortsavvy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bangkit.sortsavvy.views.main.explore.LangkahLangkahFragment
import com.bangkit.sortsavvy.views.main.explore.VideoFragment

class CaraOlahPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LangkahLangkahFragment.newInstance()
            1 -> VideoFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }

    override fun getCount(): Int {
        return 2 // Jumlah halaman
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Langkah-langkah"
            1 -> "Video"
            else -> null
        }
    }
}
