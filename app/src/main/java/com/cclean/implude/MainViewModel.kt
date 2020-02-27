package com.cclean.implude

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cclean.implude.adapter.ViewPagerAdapter
import com.cclean.implude.fragment.CommunityFragment
import com.cclean.implude.fragment.HistoryFragment
import com.cclean.implude.fragment.NoticeFragment
import com.google.android.material.tabs.TabLayout


class MainViewModel(
    fm: FragmentManager,
    public val tabLayout: CupertinoTabLayout,
    public val viewPager: ViewPager
) {
    public val viewPagerAdapter: FragmentStatePagerAdapter

    init {
        // ViewPagerAdapter 생성
        val fragments: ArrayList<Fragment   > = ArrayList()
        fragments.add(NoticeFragment())
        fragments.add(CommunityFragment())
        fragments.add(HistoryFragment())

        viewPagerAdapter = ViewPagerAdapter(fm, fragments)
    }
}