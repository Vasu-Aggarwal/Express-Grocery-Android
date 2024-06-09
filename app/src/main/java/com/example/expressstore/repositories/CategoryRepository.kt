package com.example.expressstore.repositories

import com.example.expressstore.models.responses.CategoryDto
import com.example.expressstore.services.CategoryService
import com.example.expressstore.services.TokenManager
import com.example.expressstore.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryService: CategoryService, private val tokenManager: TokenManager) {
    private val _category = MutableStateFlow<NetworkResult<List<CategoryDto>>>(NetworkResult.Loading())
    val category: StateFlow<NetworkResult<List<CategoryDto>>>
        get() = _category

    suspend fun getCategories() {
        val authToken = tokenManager.getAuthToken()
        val response = categoryService.getAllCategory("Bearer $authToken")
        if (response.isSuccessful && response.body()!=null){
            _category.emit(NetworkResult.Success(response.body()!!))
        } else {
            _category.emit(NetworkResult.Error(response.errorBody()!!.string()))
        }
    }
}