package com.implude.officialapp.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.implude.officialapp.R
import kotlinx.android.synthetic.main.layout_edit.view.*

class CupertinoEditText(context: Context, private val attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var text: String = ""

    init {
        initializeViews(context, attrs)
    }

    private fun initializeViews(
        context: Context,
        attrs: AttributeSet?
    ) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CupertinoEditText,
            0, 0
        )

        try {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.layout_edit, this)

            layout.text_title.text = a.getString(R.styleable.CupertinoEditText_title)
            layout.edittext_main.hint = a.getString(R.styleable.CupertinoEditText_hint)
            layout.edittext_main.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    text = s.toString()
                }
            })

        } finally {
            a.recycle()
        }
    }
}