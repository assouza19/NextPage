package com.br.nextpage.data.dataSource

interface UserLocalDataSource {
    fun saveToken(token: String)
    fun getToken() : String?
}