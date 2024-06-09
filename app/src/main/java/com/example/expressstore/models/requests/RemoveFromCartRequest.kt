package com.example.expressstore.models.requests

data class RemoveFromCartRequest (
    val userUuid: String,
    val product: Int
)