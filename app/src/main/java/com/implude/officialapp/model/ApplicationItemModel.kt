package com.implude.officialapp.model

import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_APPLICATION

class ApplicationItemModel (
    val title: String,
    val content: String,
    val images: List<String>,
    val date: String,
    val place: String
) : BaseItemModel(title, content, images, TYPE_APPLICATION) {

}