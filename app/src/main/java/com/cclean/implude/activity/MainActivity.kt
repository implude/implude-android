package com.cclean.implude.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cclean.implude.CupertinoTabLayout
import com.cclean.implude.R
import com.cclean.implude.adapter.ViewPagerAdapter
import com.cclean.implude.fragment.CommunityFragment
import com.cclean.implude.fragment.HistoryFragment
import com.cclean.implude.fragment.AnnounceFragment
import com.cclean.implude.model.BaseItemModel
import com.cclean.implude.model.NoticeItemModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewPagerAdapter 생성
        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(AnnounceFragment())
        fragments.add(CommunityFragment())
        fragments.add(HistoryFragment())

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
        viewpager_main.adapter = viewPagerAdapter

        // Viewpager, Tablayout Listener 연결
        layout_nav.addOnTabSelectedListener(CupertinoTabLayout.ViewPagerOnTabSelectedListener(viewpager_main))
        viewpager_main.addOnPageChangeListener(layout_nav)
    }

}
