package com.br.nextpage.domain.usecase

import com.br.nextpage.domain.models.BookDetail
import com.br.nextpage.domain.repository.BookRepository
import com.br.nextpage.domain.repository.UserRepository

internal class GetBookDetailUseCase(
    private val repository: BookRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String): BookDetail? {
        return userRepository.getToken()?.let {
            repository.getBookDetail(id, it)
        }
    }
}