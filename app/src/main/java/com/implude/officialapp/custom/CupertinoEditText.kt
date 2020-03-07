package com.implude.officialapp.custom

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.marginTop
import com.implude.officialapp.R
import kotlinx.android.synthetic.main.layout_edit.view.*

class CupertinoEditText(context: Context, private val attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var text: String = ""

    val Int.dp: Int
        get() {
            val metrics = context.resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
        }

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

            if(a.getString(R.styleable.CupertinoEditText_type) == "long")
            {
                val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                params.topMargin = 16.dp
                layout.edittext_main.apply {
                    layoutParams = params
                    inputType = InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                    minLines = 6
                    gravity = Gravity.TOP or Gravity.START
                    setHorizontallyScrolling(false)
                    isSingleLine = false
                }

                layout.view_divider.visibility = View.GONE
            }
        } finally {
            a.recycle()
        }
    }
}