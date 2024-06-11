package com.example.expressstore.services

import com.example.expressstore.models.requests.AddProductToCartRequest
import com.example.expressstore.models.requests.RemoveFromCartRequest
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.models.responses.ListCartDetailsResponse
import com.example.expressstore.models.responses.RemoveFromCartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CartService {
    @GET("/api/cart/getCartCount/{userUuid}")
    suspend fun getCartCount(@Header("Authorization") token: String, @Path("userUuid") userUuid: String): Response<CartCountResponse>

    @POST("api/cart/addToCart")
    suspend fun addToCart(@Header("Authorization") token: String, @Body addProductToCartRequest: AddProductToCartRequest): Response<AddProductToCartResponse>

    @POST("api/cart/removeFromCart")
    suspend fun removeFromCart(@Header("Authorization") token: String, @Body removeFromCartRequest: RemoveFromCartRequest): Response<RemoveFromCartResponse>

    @GET("api/cart/getCartDetails/{userUuid}")
    suspend fun getCartDetails(@Header("Authorization") token: String, @Path("userUuid") userUuid: String, @Query(value = "v") v: Int = 2): Response<ListCartDetailsResponse>
}