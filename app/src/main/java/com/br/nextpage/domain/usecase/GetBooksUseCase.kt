package com.br.nextpage.domain.usecase

import com.br.nextpage.domain.models.Book
import com.br.nextpage.domain.repository.BookRepository
import com.br.nextpage.domain.repository.UserRepository

internal class GetBooksUseCase(
    private val repository: BookRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<Book>? {
        return userRepository.getToken()?.let {
            repository.getBooks(it)
        }
    }
}