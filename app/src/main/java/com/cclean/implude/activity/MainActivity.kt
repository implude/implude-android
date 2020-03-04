package com.cclean.implude.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cclean.implude.customview.CupertinoTabLayout
import com.cclean.implude.R
import com.cclean.implude.adapter.ViewPagerAdapter
import com.cclean.implude.fragment.CommunityFragment
import com.cclean.implude.fragment.HistoryFragment
import com.cclean.implude.fragment.AnnounceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("firstLogin", 0)
        if (!sharedPreferences.getBoolean("first", false)) {
            sharedPreferences.edit().putBoolean("first", true).apply()
        }

        button_setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

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
