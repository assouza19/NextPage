package com.br.nextpage.domain.repository

import com.br.nextpage.domain.models.Book
import com.br.nextpage.domain.models.BookDetail

internal interface BookRepository {
    suspend fun getBooks(token: String): List<Book>
    suspend fun getBookDetail(idBook: String, token: String): BookDetail
}