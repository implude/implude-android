package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.R
import com.implude.officialapp.activity.ManageMemberActivity.Companion.USER_ADDED
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_add_member.*
import kotlinx.android.synthetic.main.layout_title.*


class AddMemberActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    var isUserAdded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        button_back.setOnClickListener {
            if(isUserAdded)
                setResult(USER_ADDED)
            finish()
        }

        button_add.setOnClickListener {
            //val user = UserModel("", edit_name.text, edit_mail.text, false)
            //val user = UserModel("", edit_name.text, edit_mail.text, false)
            val user = mapOf("name" to edit_name.text, "admin" to false)
            button_add.startAnimation();
            db.collection("emails")
                .document(edit_mail.text)
                .set(user)
                .addOnSuccessListener {
                    button_add.revertAnimation();
                    button_add.background = this.getDrawable(R.drawable.shape_round_accent)
                    CupertinoDialog(this@AddMemberActivity).show("성공!", "성공적으로 부원이 추가되었습니다")
                }
                .addOnFailureListener {
                    CupertinoDialog(this@AddMemberActivity).show("죄송합니다", "부원추가가 실패하였습니다")
                }
        }
    }
}