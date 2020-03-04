package com.implude.officialapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    fm: FragmentManager,
    private val fragments: List<Fragment>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return if (position < fragments.size) fragments[position] else Fragment()
    }

    override fun getCount(): Int {
        return fragments.size
    }

}