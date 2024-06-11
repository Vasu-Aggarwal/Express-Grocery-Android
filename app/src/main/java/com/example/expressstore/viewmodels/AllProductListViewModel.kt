package com.example.expressstore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.models.responses.RemoveFromCartResponse
import com.example.expressstore.repositories.AuthRepository
import com.example.expressstore.repositories.CartRepository
import com.example.expressstore.repositories.ProductRepository
import com.example.expressstore.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductListViewModel@Inject constructor(private val repository: ProductRepository, private val cartRepository: CartRepository) : ViewModel() {
    val products: StateFlow<NetworkResult<List<AllProductList>>>
        get() = repository.products

    val productCategory: StateFlow<NetworkResult<List<AllProductList>>>
        get() = repository.productCategory

    val addToCart: StateFlow<NetworkResult<AddProductToCartResponse>>
        get() = cartRepository.addToCart

    val removeFromCart: StateFlow<NetworkResult<RemoveFromCartResponse>>
        get() = cartRepository.removeFromCart

    init {
        viewModelScope.launch {
            repository.productList()
        }
    }

    fun addProductToCart(product: Int, quantity: Int){
        viewModelScope.launch {
            cartRepository.addProductToCart(product, quantity)
        }
    }

    fun removeProductFromCart(product: Int){
        viewModelScope.launch {
            cartRepository.removeFromCart(product)
        }
    }

    fun getProductsByCategoryName(categoryName: String){
        viewModelScope.launch {
            repository.getProductsByCategory(categoryName)
        }
    }
}