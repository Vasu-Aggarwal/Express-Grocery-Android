package com.example.expressstore.services

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    suspend fun loginUser(@Body user: Map<String, String>): Response<Map<String, String>>
}