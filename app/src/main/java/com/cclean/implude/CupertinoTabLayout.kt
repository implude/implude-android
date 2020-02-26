package com.cclean.implude

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout


class CupertinoTabLayout(context: Context, private val attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

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
            inflater.inflate(a.getResourceId(R.styleable.CupertinoTabLayout_layout, 0), this)


        } finally {
            a.recycle()
        }
    }


}