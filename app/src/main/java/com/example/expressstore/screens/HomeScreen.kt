package com.example.expressstore.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressstore.viewmodels.LoginViewModel

@Composable
fun HomeScreen(viewModel: LoginViewModel = hiltViewModel()){
    Text(text = "Home Screen")
}