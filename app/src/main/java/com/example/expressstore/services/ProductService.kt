package com.example.expressstore.services

import com.example.expressstore.models.responses.AllProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ProductService {
    @GET("/api/product/allProductList")
    suspend fun productList(@Header("Authorization") token: String): Response<List<AllProductList>>
}