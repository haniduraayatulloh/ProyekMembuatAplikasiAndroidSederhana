package com.example.wisatanusantara

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val overview: String,
    val description: String,
    var location: String = "",
    val imageResId: Int
) : Parcelable
