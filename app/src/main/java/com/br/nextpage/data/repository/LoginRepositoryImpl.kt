package com.br.nextpage.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.br.nextpage.domain.repository.LoginRepository

const val IS_LOGGED_KEY = "LOGADO"

class LoginRepositoryImpl(private val preferences: SharedPreferences) : LoginRepository {
    override fun login() {
        preferences.edit {
            putBoolean(IS_LOGGED_KEY, true)
        }
    }

    override fun logout() {
        preferences.edit {
            putBoolean(IS_LOGGED_KEY, false)
        }
    }

    override fun isLogged(): Boolean {
        return preferences.getBoolean(IS_LOGGED_KEY, false)
    }
}