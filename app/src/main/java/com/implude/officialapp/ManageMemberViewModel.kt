package com.implude.officialapp

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.adapter.ManageMemberRecyclerViewAdapter
import com.implude.officialapp.adapter.ManageMemberRecyclerViewAdapter.OnDeleteButtonClickListener
import com.implude.officialapp.model.UserModel

class ManageMemberViewModel : ViewModel(), OnDeleteButtonClickListener {
    public val memberList: ObservableArrayList<UserModel> = ObservableArrayList<UserModel>()
    private val db = FirebaseFirestore.getInstance()

    fun deleteUserFrom(pos: Int)
    {
        val target = memberList[pos]

        //users 목록에서 삭제
        //자체 쿼리 돌리는데 괜찮겠지...?
        db.collection("users").get().addOnSuccessListener { result ->
            for(user in result)
                if(user.toObject(UserModel::class.java).mail == target.mail)
                    user.reference.delete()
        }

        //emails 목록에서 삭제
        db.collection("emails").document(target.mail).get().addOnSuccessListener { result ->
            result.reference.delete()
        }

        memberList.removeAt(pos)
    }

    fun loadUserFrom(path: String)
    {
        db.collection(path).get().addOnSuccessListener { result ->
            for(user in result)
                memberList.add(user.toObject(UserModel::class.java))
        }
    }

    override fun onClick(v: View?, pos: Int) {
        deleteUserFrom(pos)
    }
}