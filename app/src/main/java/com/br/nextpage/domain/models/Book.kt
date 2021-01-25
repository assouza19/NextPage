package com.br.nextpage.domain.models

data class Book(
    val idBook: String,
    val author: String? = "",
    val cover: String? = "",
    val publishing: String? = "",
    val title: String? = "",
    val year: Int? = 0,
    val category: String? = "",
)