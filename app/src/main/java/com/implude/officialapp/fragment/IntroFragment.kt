package com.implude.officialapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.implude.officialapp.R
import kotlinx.android.synthetic.main.fragment_intro.view.*

class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_intro, container, false)

        layout.title.text = getArguments()!!.getString("title")
        layout.content.text = getArguments()!!.getString("content")

        if (layout.title.text == "Notice")
            layout.image.setImageResource(R.drawable.notice)
        else if (layout.title.text == "Application")
            layout.image.setImageResource(R.drawable.application)
        else if (layout.title.text == "Communicate")
            layout.image.setImageResource(R.drawable.communicate)

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}