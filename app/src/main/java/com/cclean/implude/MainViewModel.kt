package com.cclean.implude

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cclean.implude.adapter.ViewPagerAdapter
import com.cclean.implude.fragment.CommunityFragment
import com.cclean.implude.fragment.HistoryFragment
import com.cclean.implude.fragment.NoticeFragment


class MainViewModel(
    fm: FragmentManager
) {
    public val viewPagerAdapter: FragmentStatePagerAdapter

    init {
        // ViewPagerAdapter 생성
        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(NoticeFragment())
        fragments.add(CommunityFragment())
        fragments.add(HistoryFragment())

        viewPagerAdapter = ViewPagerAdapter(fm, fragments)
    }
}