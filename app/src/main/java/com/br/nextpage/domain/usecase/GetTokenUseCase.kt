package com.br.nextpage.domain.usecase

import com.br.nextpage.domain.repository.UserRepository

internal class GetTokenUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): String {
        return userRepository.getToken().orEmpty()
    }
}