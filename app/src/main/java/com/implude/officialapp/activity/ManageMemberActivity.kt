package com.implude.officialapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.R
import com.implude.officialapp.adapter.ManageMemberRecyclerViewAdapter
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.databinding.ActivityManageMemberBinding
import com.implude.officialapp.databinding.ActivitySettingBinding
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_manage_member.*
import kotlinx.android.synthetic.main.layout_title.*

class ManageMemberActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val itemList: ObservableArrayList<UserModel> = ObservableArrayList<UserModel>()
    private val adapter: ManageMemberRecyclerViewAdapter = ManageMemberRecyclerViewAdapter()

    companion object {
        public const val FAB_ADD = 100
        public const val USER_ADDED = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityManageMemberBinding = DataBindingUtil.setContentView<ActivityManageMemberBinding>(this, R.layout.activity_manage_member)
        binding.recyclerviewMain.adapter = adapter
        binding.items = itemList

        loadUser()

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
            loadUser()
    }

    fun loadUser()
    {
        db.collection("users").get().addOnSuccessListener { result ->
            for(user in result)
                itemList.add(user.toObject(UserModel::class.java))
        }
    }
}
