package com.example.expressstore.models.requests

data class UserRegisterRequest(
    val userUuid: String,
    val username: String,
    val email: String,
    val mobile: String,
    val name: String,
    val isCoupon: Boolean
)
