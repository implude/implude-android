package com.implude.officialapp.model

import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_APPLICATION

data class ApplicationItemModel(
    val title: String = "",
    val content: String = "",
    val images: List<String>? = listOf(),
    val date: String = "",
    val place: String = ""
) : BaseItemModel(title, content, images, "",TYPE_APPLICATION)