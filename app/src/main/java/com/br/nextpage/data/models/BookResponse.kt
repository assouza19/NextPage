package com.br.nextpage.data.models

import kotlinx.serialization.SerialName

data class BookResponseItem(
    @SerialName("idbook") val idBook: String,
    @SerialName("author") val author: String? = "",
    @SerialName("cover") val cover: String? = "",
    @SerialName("publishing") val publishing: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("year") val year: Int? = 0,
    @SerialName("category") val category: String? = "",
)