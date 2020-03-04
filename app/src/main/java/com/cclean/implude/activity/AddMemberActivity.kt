package com.cclean.implude.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cclean.implude.R
import com.cclean.implude.databinding.ActivityAddMemberBinding
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