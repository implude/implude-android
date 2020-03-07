package com.implude.officialapp.model

import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_NOTICE

class NoticeItemModel (
    val title: String,
    val content: String,
    val images: List<String>,
    val link: String,
    val isImportant: Boolean,
    val isAllowedComment: Boolean
) : BaseItemModel(title, content, images, TYPE_NOTICE) {

}