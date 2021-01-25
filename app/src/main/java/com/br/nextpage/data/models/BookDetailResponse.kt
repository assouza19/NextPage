package com.br.nextpage.data.models

import kotlinx.serialization.SerialName

data class BookDetailResponse(
    @SerialName("idbook") val idBook: String,
    @SerialName("author") val author: String? = "",
    @SerialName("cover") val cover: String? = "",
    @SerialName("category") val category: String? = "",
    @SerialName("publishing") val publishing: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("year") val year: Int? = 0,
    @SerialName("offers") val buttons: List<BookButtonResponse>? = null,
    @SerialName("summary") val description: String? = ""
)

data class BookButtonResponse(
    @SerialName("store") val description: String,
    @SerialName("link") val url: String
)