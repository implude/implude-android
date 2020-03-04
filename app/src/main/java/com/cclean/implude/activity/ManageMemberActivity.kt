package com.cclean.implude.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.cclean.implude.R
import com.cclean.implude.adapter.AnnounceRecyclerViewAdapter
import com.cclean.implude.adapter.ManageMemberRecyclerViewAdapter
import com.cclean.implude.databinding.ActivityManageMemberBinding
import com.cclean.implude.databinding.ActivitySettingBinding
import com.cclean.implude.model.BaseItemModel
import com.cclean.implude.model.UserModel
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
