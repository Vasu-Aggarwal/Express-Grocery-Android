package com.example.expressstore.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.repositories.AuthRepository
import com.example.expressstore.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val user: StateFlow<NetworkResult<Map<String, String>?>>
    get() = repository.user

    fun login(username: String, password: String){
        viewModelScope.launch {
            repository.login(username, password)
        }
    }

}