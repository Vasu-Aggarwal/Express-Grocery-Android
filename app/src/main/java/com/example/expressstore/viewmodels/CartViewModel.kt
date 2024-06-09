package com.example.expressstore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.models.responses.ListCartDetailsResponse
import com.example.expressstore.repositories.CartRepository
import com.example.expressstore.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel(){
    val cartCount: StateFlow<NetworkResult<CartCountResponse>>
        get() = cartRepository.cartCount

    val cartDetails: StateFlow<NetworkResult<ListCartDetailsResponse>>
        get() = cartRepository.cartDetails

    init {
        getCartCount()
    }

    fun getCartCount() {
        viewModelScope.launch {
            cartRepository.getCartCount()
        }
    }

    fun getCartDetails(){
        viewModelScope.launch {
            cartRepository.getCartDetails()
        }
    }

    fun increaseLocalCartCount(){
        cartRepository.incrementCartCount()
    }

    fun decreaseLocalCartCount() {
        cartRepository.decrementCartCount()
    }

}