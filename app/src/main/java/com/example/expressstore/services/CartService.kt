package com.example.expressstore.services

import com.example.expressstore.models.requests.AddProductToCartRequest
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.models.responses.CartCountResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {
    @GET("/api/cart/getCartCount/{userUuid}")
    suspend fun getCartCount(@Header("Authorization") token: String, @Path("userUuid") userUuid: String): Response<CartCountResponse>

    @POST("api/cart/addToCart")
    suspend fun addToCart(@Header("Authorization") token: String, @Body addProductToCartRequest: AddProductToCartRequest): Response<AddProductToCartResponse>
}