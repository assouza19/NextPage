package com.br.nextpage.data.models

import kotlinx.serialization.SerialName

data class LoginResponse(
    @SerialName("token") val token: Token,
    @SerialName("url") val url: String
)

data class Token(
    @SerialName("access_token") val access_token: String,
    @SerialName("token_type") val token_type: String
)