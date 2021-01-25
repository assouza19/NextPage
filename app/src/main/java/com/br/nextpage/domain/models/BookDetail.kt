package com.br.nextpage.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDetail(
    val idBook: String,
    val author: String? = "",
    val cover: String? = "",
    val category: String? = "",
    val publishing: String? = "",
    val title: String? = "",
    val year: Int? = 0,
    val buttons: List<BookButton>? = null,
    val description: String? = ""
) : Parcelable

@Parcelize
data class BookButton(
    val description: String,
    val url: String
) : Parcelable