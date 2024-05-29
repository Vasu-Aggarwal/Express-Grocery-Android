package com.example.expressstore.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.expressstore.services.AuthService
import com.example.expressstore.services.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService,
                                         private val tokenManager: TokenManager,
                                        @ApplicationContext private val context: Context){

    private val _user = MutableStateFlow<Map<String, String>?>(emptyMap())
    val user : StateFlow<Map<String, String>?>
    get() = _user

    suspend fun login(username: String, password: String){
        val body = mapOf(
            "username" to username,
            "password" to password
        )
        val response = authService.loginUser(body)
        if (response.isSuccessful && response.body() != null){
            val authToken = response.body()!!.get("token")
            val refreshToken = response.body()!!.get("refreshToken")
            tokenManager.saveAuthToken(authToken, refreshToken)
            _user.emit(response.body())
        } else {
            Toast.makeText(context, response.errorBody()?.string(), Toast.LENGTH_SHORT).show()
        }
    }
}