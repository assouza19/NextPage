package com.br.nextpage.data.dataSource

import com.br.nextpage.data.models.LoginResponse
import retrofit2.Response

interface UserDataSource {
    suspend fun login(): Response<LoginResponse>
}