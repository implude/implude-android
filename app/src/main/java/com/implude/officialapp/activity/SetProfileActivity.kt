package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.implude.officialapp.R
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_setprofile.*

class SetProfileActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var userDoc : DocumentSnapshot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setprofile)

        val currentUser = FirebaseAuth.getInstance().currentUser
        val test = currentUser?.email

//            if(documents.isEmpty)
//            {
//                CupertinoDialog(this@SetProfileActivity).show("죄송합니다", "계정에 문제가 생긴 것 같습니다\n해결을 위해 동장혹은 부동장에게 문의하세요")
//                finish()
//            } else {
//                userDoc = documents.documents[0]
//                val user = userDoc.toObject(UserModel::class.java)
//                if (user != null)
//                    Glide.with(this@SetProfileActivity).load("https://lh3.googleusercontent.com/a-/AOh14GhEn2hFweV2LNhC9ADyhwOdMulVkEVbQ-kHwvMzMg=s96-c").into(img_profile as ImageView)
//            }

        button_next.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}