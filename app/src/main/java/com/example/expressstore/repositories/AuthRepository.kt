package com.example.expressstore.repositories

import android.util.Log
import com.example.expressstore.services.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService){

    private val _user = MutableStateFlow<Map<String, String>?>(emptyMap())
    val user : StateFlow<Map<String, String>?>
    get() = _user

    suspend fun login(email: String, password: String){
        val body = mapOf(
            "username" to email,
            "password" to password
        )
        val response = authService.loginUser(body)
        if (response.isSuccessful && response.body() != null){
            Log.i("login", "login: "+response.body().toString())
            _user.emit(response.body())
        }
    }
}