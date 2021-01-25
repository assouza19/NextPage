package com.br.nextpage.di

import com.br.nextpage.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface UsersService {

    @POST("/v1/user/youtube/auth")
    suspend fun doLogin(): Response<LoginResponse>
}