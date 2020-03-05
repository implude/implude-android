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

    init {
//        val testUser = UserModel("https://i.ytimg.com/vi/XplrxSSrja0/maxresdefault.jpg", "장종우", "mcpgclean@gmail.com", false, "")
//        itemList.add(testUser)
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

        db.collection("users").get().addOnSuccessListener { result ->
                for(user in result)
                    itemList.add(user.toObject(UserModel::class.java))
        }

//        val query = docRef.whereEqualTo("mail", account!!.email)
//        query.get().addOnSuccessListener { documents ->
//            if(documents.isEmpty)
//            {
//                CupertinoDialog(this@ManageMemberActivity).show("죄송합니다", "데이터를 불러오는 과정에 문제가 생긴 것 같습니다")
//            } else {
//                userData = documents.documents[0].toObject(UserModel::class.java)!!
//            }
//        }
    }
}
