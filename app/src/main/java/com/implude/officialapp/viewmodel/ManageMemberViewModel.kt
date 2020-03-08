package com.implude.officialapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.adapter.ItemDeletable
import com.implude.officialapp.model.UserModel
import kotlinx.coroutines.tasks.await

class ManageMemberViewModel : ViewModel(), ItemDeletable {
    val memberList = ObservableArrayList<UserModel>()
    private val pendingUsers = ObservableArrayList<UserModel>()
    private val db = FirebaseFirestore.getInstance()

    private fun deleteUserFrom(pos: Int) {
        val target = memberList[pos]

        //users 목록에서 삭제
        //자체 쿼리 돌리는데 괜찮겠지...?
        db.collection("users").get().addOnSuccessListener { result ->
            for (user in result)
                if (user.toObject(UserModel::class.java).mail == target.mail)
                    user.reference.delete()
        }

        //emails 목록에서 삭제
        db.collection("emails").document(target.mail).get().addOnSuccessListener { result ->
            result.reference.delete()
        }

        memberList.removeAt(pos)
    }

    suspend fun loadUserFrom(path: String) {
        db.collection(path).get().await().forEach {
            memberList.add(it.toObject(UserModel::class.java))
        }
        loadPendingUsers()
    }

    override fun onDeleteItemButtonClick(deleteItemPosition: Int) {
        deleteUserFrom(deleteItemPosition)
    }

    private suspend fun loadPendingUsers() {
        val memberEmails = memberList.map { it.mail }
        db.collection("emails").get().await().forEach {
            val email = it.id
            if (!memberEmails.contains(email)) {
                val user = it.toObject(UserModel::class.java)
                user.mail = email
                pendingUsers.add(user)
            }
        }
    }
}