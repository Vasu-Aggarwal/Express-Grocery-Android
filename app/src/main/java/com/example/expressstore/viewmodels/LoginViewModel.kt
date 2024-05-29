package com.example.expressstore.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val user: StateFlow<Map<String, String>?>
    get() = repository.user

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState>
        get() = _loginState

    fun login(username: String, password: String){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            repository.login(username, password)
            _loginState.value = LoginState.Success
        }
    }
//    init {
//        viewModelScope.launch {
//            repository.login("Tiffany.Bogan29@yahoo.com", "vasu")
//        }
//    }

}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}