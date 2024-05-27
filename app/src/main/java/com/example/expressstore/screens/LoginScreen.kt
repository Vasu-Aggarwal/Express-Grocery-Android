package com.example.expressstore.screens

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expressstore.viewmodels.LoginViewModel

@Composable
fun loginScreen(){
    val loginViewModel: LoginViewModel = viewModel()
    val user: State<Map<String, String>?> = loginViewModel.user.collectAsState()
}

@Preview
@Composable
fun loginScreenPreview(){
    TextField(value = "username", onValueChange = {})
}