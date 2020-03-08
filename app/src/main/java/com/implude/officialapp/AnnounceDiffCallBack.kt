package com.implude.officialapp

import androidx.recyclerview.widget.DiffUtil
import com.implude.officialapp.model.BaseItemModel

class AnnounceDiffCallBack(val oldList: List<BaseItemModel>,val newList: List<BaseItemModel>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}