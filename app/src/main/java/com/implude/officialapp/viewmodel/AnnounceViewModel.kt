package com.implude.officialapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_APPLICATION
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_NOTICE
import com.implude.officialapp.model.ApplicationItemModel
import com.implude.officialapp.model.BaseItemModel
import com.implude.officialapp.model.NoticeItemModel
import com.implude.officialapp.model.UserModel
import kotlinx.coroutines.tasks.await
import java.lang.reflect.Array

class AnnounceViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    val itemList: ObservableArrayList<BaseItemModel> = ObservableArrayList<BaseItemModel>()

    //TODO 페이징 해야지... 언젠가...?
    suspend fun loadAnnounce() {
        val items = ArrayList<BaseItemModel>()

        db.collection("announce").get().await().forEach {
            val type = Integer.parseInt(it.get("type").toString())

            if(type == TYPE_APPLICATION) {
                val temp = it.toObject(ApplicationItemModel::class.java)
                temp.id = it.reference.path
                items.add(temp)
            }
            else if(type == TYPE_NOTICE)
            {
                val temp = it.toObject(NoticeItemModel::class.java)
                temp.id = it.reference.path
                items.add(temp)
            }
        }

        itemList.clear()
        itemList.addAll(items)
    }


}
