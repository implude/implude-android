package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.implude.officialapp.R
import com.implude.officialapp.databinding.ActivitySettingBinding
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_title.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivitySettingBinding = DataBindingUtil.setContentView<ActivitySettingBinding>(this, R.layout.activity_setting)

        button_back.setOnClickListener {
            finish()
        }

        layout_manage_member.setOnClickListener {
            startActivity(Intent(this, ManageMemberActivity::class.java))
        }
    }
}