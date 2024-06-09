package com.example.expressstore.services

import com.example.expressstore.models.responses.CategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryService {
    @GET("api/category/getCategoryList")
    suspend fun getAllCategory(@Header ("Authorization") token: String): Response<List<CategoryDto>>
}