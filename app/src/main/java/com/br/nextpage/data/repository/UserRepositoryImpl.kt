package com.br.nextpage.data.repository

import com.br.nextpage.data.dataSource.UserDataSource
import com.br.nextpage.data.dataSource.UserLocalDataSource
import com.br.nextpage.data.models.LoginResponse
import com.br.nextpage.domain.models.Login
import com.br.nextpage.domain.models.Token
import com.br.nextpage.domain.repository.UserRepository
import retrofit2.Response

internal class UserRepositoryImpl(
    private val dataSource: UserDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun login(): Login {
        return mapToDomain(dataSource.login())
    }

    private fun mapToDomain(login: Response<LoginResponse>): Login {
        return Login(
            token = Token(
                access_token = login.body()?.token?.access_token.orEmpty(),
                token_type = login.body()?.token?.token_type.orEmpty()
            ),
            url = login.body()?.url.orEmpty()
        )
    }

    override fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override fun getToken(): String? {
        return localDataSource.getToken()
    }
}