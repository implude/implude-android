package com.implude.officialapp.model

public open class BaseItemModel(
    title: String,
    content: String,
    images: List<String>?,
    var id: String = "",
    val type: Int
)