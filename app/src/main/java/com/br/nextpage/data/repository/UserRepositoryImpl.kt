package com.br.nextpage.data.repository

import androidx.core.content.edit
import com.br.nextpage.data.dataSource.UserDataSource
import com.br.nextpage.data.dataSource.UserLocalDataSource
import com.br.nextpage.domain.models.Login
import com.br.nextpage.domain.repository.UserRepository
import retrofit2.Response

internal class UserRepositoryImpl(
    private val dataSource: UserDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun login(): Login {
        return dataSource.login().mapToDomain()
    }

    override fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override fun getToken(): String? {
        return localDataSource.getToken()
    }
}

private fun <T> T?.orNull() = null
