package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.implude.officialapp.R
import com.implude.officialapp.adapter.ViewPagerAdapter
import com.implude.officialapp.fragment.IntroFragment
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_main.viewpager_main

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val notice = Bundle()
        notice.apply {
            putString("title", "Notice")
            putString("content", "동아리원에게 꼭 전달해야하는 사항이나\n" +
                    "정보공유, 설문조사 등의 여러가지 기능을\n" +
                    "이용할 수 있습니다")
        }

        val application = Bundle()
        application.apply {
            putString("title", "Application")
            putString("content", "동아리원과 함께 대회에 나가거나\n" +
                    "학교에 행사가 있을때 애플리케이션에서\n" +
                    "쉽게 신청하고 일정을 알림받을 수 있습니다.")
        }

        val communicate = Bundle()
        communicate.apply {
            putString("title", "Communication")
            putString("content", "동아리원과 애플리케이션을 사용하여\n" +
                    "모르는 내용을 질문하거나 게시글을 작성할 수 있습니다")
        }

        button_next.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(IntroFragment().apply { arguments = notice })
        fragments.add(IntroFragment().apply { arguments = application })
        fragments.add(IntroFragment().apply { arguments = communicate })

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
        viewpager_main.adapter = viewPagerAdapter
        viewpager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 2)
                    button_next.visibility = View.VISIBLE
                else
                    button_next.visibility = View.GONE
            }
        })
    }
}