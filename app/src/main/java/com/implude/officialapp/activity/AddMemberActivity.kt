package com.implude.officialapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.implude.officialapp.R
import com.implude.officialapp.databinding.ActivityAddMemberBinding
import kotlinx.android.synthetic.main.layout_title.*

class AddMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityAddMemberBinding = DataBindingUtil.setContentView<ActivityAddMemberBinding>(this, R.layout.activity_add_member)

        button_back.setOnClickListener {
            finish()
        }
    }
}