package com.br.nextpage.domain.repository

interface LoginRepository {
    fun login()
    fun logout()
    fun isLogged() : Boolean
}