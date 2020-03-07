package com.implude.officialapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.implude.officialapp.R
import com.implude.officialapp.databinding.ActivityAddAnnounceBinding

class AddAnnounceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityAddAnnounceBinding = DataBindingUtil.setContentView<ActivityAddAnnounceBinding>(this, R.layout.activity_add_announce)

    }
}