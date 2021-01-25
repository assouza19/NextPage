package com.br.nextpage.data.repository

import com.br.nextpage.data.dataSource.BookLocalDataSource
import com.br.nextpage.data.dataSource.BookRemoteDataSource
import com.br.nextpage.domain.models.Book
import com.br.nextpage.domain.models.BookDetail
import com.br.nextpage.domain.repository.BookRepository

internal class BookRepositoryImpl(
    private val localDataSource: BookLocalDataSource,
    private val remoteDataSource: BookRemoteDataSource
) : BookRepository {

    override suspend fun getBooks(token: String): List<Book> {
        return remoteDataSource.getBooks(token)
    }

    override suspend fun getBookDetail(idBook: String, token: String): BookDetail {
        return remoteDataSource.getBookDetail(idBook, token)
    }
}

