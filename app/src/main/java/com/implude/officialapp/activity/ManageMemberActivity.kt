package com.implude.officialapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.implude.officialapp.R
import com.implude.officialapp.adapter.ManageMemberRecyclerViewAdapter
import com.implude.officialapp.databinding.ActivityManageMemberBinding
import com.implude.officialapp.databinding.ActivitySettingBinding
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_manage_member.*
import kotlinx.android.synthetic.main.layout_title.*

class ManageMemberActivity : AppCompatActivity() {
    private val itemList: ObservableArrayList<UserModel> = ObservableArrayList<UserModel>()
    private val adapter: ManageMemberRecyclerViewAdapter = ManageMemberRecyclerViewAdapter()

    init {
        val testUser = UserModel("", "장종우", "mcpgclean@gmail.com")
        itemList.add(testUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityManageMemberBinding = DataBindingUtil.setContentView<ActivityManageMemberBinding>(this, R.layout.activity_manage_member)
        binding.recyclerviewMain.adapter = adapter
        binding.items = itemList

        button_back.setOnClickListener {
            finish()
        }

        fab_add.setOnClickListener {
            startActivity(Intent(this, AddMemberActivity::class.java))
        }

    }
}
