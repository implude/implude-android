package com.cclean.implude.model

import androidx.appcompat.app.AppCompatActivity
import com.cclean.implude.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_APPLICATION
import kotlin.jvm.internal.Ref

class ApplicationItemModel (
    val title: String,
    val content: String,
    val image: List<String>,
    val date: String,
    val place: String
) : BaseItemModel(title, content, image, TYPE_APPLICATION) {

}