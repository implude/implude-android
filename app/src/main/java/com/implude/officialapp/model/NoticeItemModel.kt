package com.implude.officialapp.model

import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_NOTICE

data class NoticeItemModel   (
    val title: String = "",
    val content: String = "",
    val images: List<String>? = listOf(),
    val link: String = "",
    val isImportant: Boolean = false,
    val isAllowedComment: Boolean = false
) : BaseItemModel(title, content, images, "", TYPE_NOTICE)