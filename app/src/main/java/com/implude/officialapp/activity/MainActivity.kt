package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.implude.officialapp.custom.CupertinoTabLayout
import com.implude.officialapp.R
import com.implude.officialapp.adapter.ViewPagerAdapter
import com.implude.officialapp.fragment.CommunityFragment
import com.implude.officialapp.fragment.HistoryFragment
import com.implude.officialapp.fragment.AnnounceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ADD_ANNOUNCE = 100
        private const val ADD_ANNOUNCE_SUCCESS = 101
    }

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

        fab.setOnClickListener {
            if (viewpager_main.currentItem == 0)
                startActivityForResult(Intent(this, AddAnnounceActivity::class.java), ADD_ANNOUNCE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //if(requestCode== ADD_ANNOUNCE && resultCode== ADD_ANNOUNCE_SUCCESS)
    }
}
