package com.br.nextpage.data.dataSource

import android.content.SharedPreferences
import androidx.core.content.edit

const val TOKEN = "token"

internal class UserLocalDataSourceImpl(
    private val preferences: SharedPreferences
) : UserLocalDataSource {
    override fun saveToken(token: String) {
        preferences.edit {
            putString(TOKEN, token)
        }
    }

    override fun getToken(): String? = preferences.getString(TOKEN, null)
}