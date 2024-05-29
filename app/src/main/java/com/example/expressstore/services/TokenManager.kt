package com.example.expressstore.services

import android.content.SharedPreferences

class TokenManager (private val sharedPreferences: SharedPreferences) {

    fun saveAuthToken(authToken: String?, refreshToken: String?) {
        with(sharedPreferences.edit()) {
            putString("auth_token", authToken)
            putString("refresh_token", refreshToken)
            apply()
        }
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString("refresh_token", null)
    }

    fun clearTokens() {
        with(sharedPreferences.edit()) {
            remove("auth_token")
            remove("refresh_token")
            apply()
        }
    }
}