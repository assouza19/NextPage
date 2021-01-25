package com.br.nextpage.data.repository

import com.br.nextpage.data.dataSource.BookLocalDataSource
import com.br.nextpage.data.dataSource.BookRemoteDataSource
import com.br.nextpage.data.models.BookDetailResponse
import com.br.nextpage.data.models.BookResponseItem
import com.br.nextpage.domain.models.Book
import com.br.nextpage.domain.models.BookDetail
import com.br.nextpage.domain.repository.BookRepository

internal class BookRepositoryImpl(
    private val localDataSource: BookLocalDataSource,
    private val remoteDataSource: BookRemoteDataSource
) : BookRepository {

    override suspend fun getBooks(token: String): List<Book> {
        return mapToDomainList(remoteDataSource.getBooks(token))
    }

    override suspend fun getBookDetail(idBook: String, token: String): BookDetail {
        return mapToDomainDetail(remoteDataSource.getBookDetail(idBook, token))
    }

    private fun mapToDomainDetail(bookDetail: BookDetailResponse): BookDetail {
        return BookDetail(
            idBook = bookDetail.idBook,
            author = bookDetail.author,
            cover = bookDetail.cover,
            publishing = bookDetail.publishing,
            title = bookDetail.title,
            year = bookDetail.year,
            category = bookDetail.category
        )
    }

    private fun mapToDomainList(books: List<BookResponseItem>): List<Book> {
        return books.map {
            Book(
                idBook = it.idBook,
                author = it.author,
                cover = it.cover,
                publishing = it.publishing,
                title = it.title,
                year = it.year,
                category = it.category
            )
        }
    }
}

