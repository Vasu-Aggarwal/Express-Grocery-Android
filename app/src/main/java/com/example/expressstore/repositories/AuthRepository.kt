package com.example.expressstore.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.expressstore.di.AppModule
import com.example.expressstore.models.ErrorResponse
import com.example.expressstore.services.AuthService
import com.example.expressstore.services.TokenManager
import com.example.expressstore.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService,
                                         private val tokenManager: TokenManager){

    private val _user = MutableStateFlow<NetworkResult<Map<String, String>?>>(NetworkResult.Loading())
    val user : StateFlow<NetworkResult<Map<String, String>?>>
    get() = _user

    suspend fun login(username: String, password: String){
        val body = mapOf(
            "username" to username,
            "password" to password
        )
        val response = authService.loginUser(body)
        if (response.isSuccessful && response.body()!=null){
            val authToken = response.body()!!.get("token")
            val refreshToken = response.body()!!.get("refreshToken")
            tokenManager.saveAuthToken(authToken, refreshToken)
            _user.emit(NetworkResult.Success(response.body()))
        } else {
            // Parse the error body to extract the error message
            val rawError = response.errorBody()?.string()
            val errorResponse = rawError.let { errorBody ->
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                gson.fromJson<ErrorResponse>(errorBody, type)
            }
            val errorBody = mapOf(
                "message" to errorResponse.message,
                "status" to errorResponse.status.toString()
            )
            _user.emit(NetworkResult.Error(errorBody.get("message")))
            errorResponse?.let {
                _user.emit(NetworkResult.Error("Something went wrong !!"))
            } ?: run {
                Log.i("Error", "login: ${response.code()}")
            }
        }
    }
}