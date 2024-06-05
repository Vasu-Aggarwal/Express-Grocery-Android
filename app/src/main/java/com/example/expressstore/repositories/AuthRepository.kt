package com.example.expressstore.repositories

import android.util.Log
import com.example.expressstore.models.requests.UserLoginRequest
import com.example.expressstore.models.responses.ErrorResponse
import com.example.expressstore.models.responses.UserLoginResponse
import com.example.expressstore.services.AuthService
import com.example.expressstore.services.TokenManager
import com.example.expressstore.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService,
                                         private val tokenManager: TokenManager
){

    private val _user = MutableStateFlow<NetworkResult<UserLoginResponse>>(NetworkResult.Idle())
    val user : StateFlow<NetworkResult<UserLoginResponse>>
    get() = _user

    suspend fun login(username: String, password: String){
        _user.emit(NetworkResult.Loading())
        val requestBody = UserLoginRequest(username, password)
        val response = authService.loginUser(requestBody)
        if (response.isSuccessful && response.body()!=null){
            val authToken = response.body()!!.token
            val refreshToken = response.body()!!.refreshToken
            tokenManager.saveAuthToken(authToken, refreshToken)
            Log.e("I came here", "login: "+authToken)
            _user.emit(NetworkResult.Success(response.body()!!))
        } else {
            // Parse the error body to extract the error message
            val rawError = response.errorBody()?.string()
            Log.i("NEW Response", "login: "+response.errorBody()?.string())
            val errorResponse = rawError.let { errorBody ->
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                gson.fromJson<ErrorResponse>(errorBody, type)
            }
            errorResponse?.let {
                _user.emit(NetworkResult.Error(errorResponse.message))
            } ?: run {
                Log.i("Error", "login: ${response.code()}")
            }
        }
    }
}