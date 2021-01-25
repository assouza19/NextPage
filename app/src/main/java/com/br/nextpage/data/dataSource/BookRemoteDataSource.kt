package com.br.nextpage.data.dataSource

import com.br.nextpage.data.models.BookDetailResponse
import com.br.nextpage.data.models.BookResponseItem

interface BookRemoteDataSource {

    suspend fun getBooks(token: String) : List<BookResponseItem>
    suspend fun getBookDetail(idBook: String, token: String) : BookDetailResponse
}