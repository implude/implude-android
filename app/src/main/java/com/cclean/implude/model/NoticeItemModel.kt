package com.cclean.implude.model

import androidx.appcompat.app.AppCompatActivity
import com.cclean.implude.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_NOTICE

class NoticeItemModel (
    val title: String,
    val content: String,
    val image: List<String>,
    val link: String,
    val isImportant: Boolean,
    val isAllowedComment: Boolean
) : BaseItemModel(title, content, image, TYPE_NOTICE) {

}