package com.cclean.implude

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.airbnb.paris.extensions.style
import com.google.android.material.tabs.TabLayout
import java.util.*


class CupertinoTabLayout(context: Context, private val attrs: AttributeSet?):
    LinearLayout(context, attrs) , ViewPager.OnPageChangeListener {
    private lateinit var onTabSelectedListner: OnTabSelectedListener

    private var tabList: ArrayList<TextView> = ArrayList()
    private var tabNumber = 0
    private var selected = 0
    private var selectedStyle = 0
    private var disabledStyle = 0

    init {
        initializeViews(context, attrs)
    }

    private fun initializeViews(
        context: Context,
        attrs: AttributeSet?
    ) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CupertinoTabLayout,
            0, 0
        )

        try {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(a.getResourceId(R.styleable.CupertinoTabLayout_layout, 0), this)

            tabNumber = a.getInteger(R.styleable.CupertinoTabLayout_tabNum,0)
            selectedStyle = a.getResourceId(R.styleable.CupertinoTabLayout_selectedTheme, 0)
            disabledStyle = a.getResourceId(R.styleable.CupertinoTabLayout_disabledTheme, 0)

            for (v in 0 until tabNumber)
            {
                tabList.add(layout.findViewWithTag(v.toString()))
                tabList[v].setOnClickListener {
                    selected = Integer.parseInt(it.tag as String)
                    onSelected(selected)
                    onTabSelectedListner.onTabSelected(selected)
                }
            }
        } finally {
            a.recycle()
        }
    }

    private fun onSelected(i: Int)
    {
        for (v in 0 until tabNumber)
        {
            if (v == i)
                tabList[v].style(selectedStyle)
            else
                tabList[v].style(disabledStyle)
        }
    }

    public fun addOnTabSelectedListener(viewPager: ViewPager)
    {
        onTabSelectedListner = ViewPagerOnTabSelectedListener(viewPager)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        selected = position
        onSelected(position)
    }

    class ViewPagerOnTabSelectedListener(private val viewPager: ViewPager) :
        OnTabSelectedListener {
        override fun onTabSelected(position: Int) {
            viewPager.currentItem = position
        }
    }

    interface OnTabSelectedListener {
        fun onTabSelected(position: Int)
    }
}