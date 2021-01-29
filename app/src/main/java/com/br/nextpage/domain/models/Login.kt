package com.br.nextpage.domain.models

data class Login(
    val token: Token,
    val url: String
)

data class Token(
    val access_token: String,
    val token_type: String
)