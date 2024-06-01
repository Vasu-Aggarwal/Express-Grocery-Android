package com.example.expressstore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.repositories.AuthRepository
import com.example.expressstore.repositories.ProductRepository
import com.example.expressstore.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductListViewModel@Inject constructor(private val repository: ProductRepository) : ViewModel() {
    val products: StateFlow<NetworkResult<List<AllProductList>>>
        get() = repository.products

    init {
        viewModelScope.launch {
            repository.productList()
        }
    }
    fun getAllProducts() {
        viewModelScope.launch {
            repository.productList()
        }
    }
}