package com.example.expressstore.services

import com.example.expressstore.models.requests.UserLoginRequest
import com.example.expressstore.models.responses.UserLoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    suspend fun loginUser(@Body user: UserLoginRequest): Response<UserLoginResponse>
}