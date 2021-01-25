package com.br.nextpage.di

import com.br.nextpage.data.models.BookDetailResponse
import com.br.nextpage.data.models.BookResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BooksService {
    @GET("/v1/books")
    suspend fun getBooks(
        @Header("Authorization") token: String
    ): Response<List<BookResponseItem>>

    @GET("/v1/books/{book_id}")
    suspend fun getBooksDetail(
        @Path("book_id") book_id: String,
        @Header("Authorization") token: String
    ): Response<BookDetailResponse>
}