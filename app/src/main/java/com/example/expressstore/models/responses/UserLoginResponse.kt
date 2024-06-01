package com.example.expressstore.models.responses

data class UserLoginResponse(val token: String, val refreshToken: String)