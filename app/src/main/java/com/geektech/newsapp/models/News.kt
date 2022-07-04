package com.geektech.newsapp.models

import android.text.Editable
import java.io.Serializable

data class News(
    var title: String,
    val createdAt: Long
) : Serializable
