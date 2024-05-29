package com.example.expressstore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expressstore.screens.LoginScreen

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable(route = "login"){
            LoginScreen()
        }
    }
}