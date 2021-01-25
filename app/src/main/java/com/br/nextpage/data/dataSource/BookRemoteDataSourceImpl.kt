package com.br.nextpage.data.dataSource

import com.br.nextpage.data.models.BookDetailResponse
import com.br.nextpage.data.models.BookResponseItem
import com.br.nextpage.di.BooksService

class BookRemoteDataSourceImpl(
    val api: BooksService
): BookRemoteDataSource {
    override suspend fun getBooks(token: String) : List<BookResponseItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetail(idBook: String, token: String) : BookDetailResponse {
        TODO("Not yet implemented")
    }
}