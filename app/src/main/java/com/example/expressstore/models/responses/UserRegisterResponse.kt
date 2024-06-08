package com.example.expressstore.models.responses

data class UserRegisterResponse(
    val userUuid: String,
    val username: String,
    val email: String,
    val mobile: String,
    val name: String,
    val isCoupon: Boolean
)
