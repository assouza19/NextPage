package com.br.nextpage.domain.repository

import com.br.nextpage.domain.models.Login

interface UserRepository {
    fun saveToken(token: String)
    fun getToken(): String?
    suspend fun login() : Login
}