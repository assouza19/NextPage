package com.br.nextpage.data.dataSource

import com.br.nextpage.data.models.LoginResponse
import com.br.nextpage.di.UsersService
import retrofit2.Response

class UserDataSourceImpl(
    private val api: UsersService
) : UserDataSource {

    override suspend fun login(): Response<LoginResponse> {
        return api.doLogin()
    }
}