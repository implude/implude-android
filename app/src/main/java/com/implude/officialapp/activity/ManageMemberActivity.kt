package com.implude.officialapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.AnnounceViewModel
import com.implude.officialapp.ManageMemberViewModel
import com.implude.officialapp.R
import com.implude.officialapp.adapter.ManageMemberRecyclerViewAdapter
import com.implude.officialapp.databinding.ActivityManageMemberBinding
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_manage_member.*
import kotlinx.android.synthetic.main.layout_title.*

class ManageMemberActivity : AppCompatActivity() {
    private lateinit var viewModel: ManageMemberViewModel
    private val memberAdapter: ManageMemberRecyclerViewAdapter = ManageMemberRecyclerViewAdapter()

    //private val waitingList: ObservableArrayList<UserModel> = ObservableArrayList<UserModel>()
    //private val waitingAdapter: ManageMemberRecyclerViewAdapter = ManageMemberRecyclerViewAdapter()

    //TODO: 초대 수락중인 부원 표시
    companion object {
        public const val FAB_ADD = 100
        public const val USER_ADDED = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityManageMemberBinding = DataBindingUtil.setContentView<ActivityManageMemberBinding>(this, R.layout.activity_manage_member)
        viewModel = ViewModelProviders.of(this).get(ManageMemberViewModel::class.java)

        binding.recyclerviewMain.adapter = memberAdapter
        binding.viewModel = viewModel

        viewModel.loadUserFrom("users")

        button_back.setOnClickListener {
            finish()
        }

        fab_add.setOnClickListener {
            startActivityForResult(Intent(this, AddMemberActivity::class.java), FAB_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==FAB_ADD && resultCode==USER_ADDED)
            viewModel.loadUserFrom("users")
    }
}
