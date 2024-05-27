package com.example.expressstore.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val user: StateFlow<Map<String, String>?>
        get() = repository.user

    init {
        viewModelScope.launch {
            repository.login("Elliott41@yahoo.com", "vasu")
        }
    }

}