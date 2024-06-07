package com.example.expressstore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.repositories.CartRepository
import com.example.expressstore.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel(){
    val cartCount: StateFlow<NetworkResult<CartCountResponse>>
        get() = cartRepository.cartCount

    init {
        viewModelScope.launch {
            cartRepository.getCartCount()
        }
    }

    fun getCartCount() {
        viewModelScope.launch {
            cartRepository.getCartCount()
        }
    }
}