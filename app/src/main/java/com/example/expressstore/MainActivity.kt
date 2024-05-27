package com.example.expressstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.expressstore.screens.loginScreen
import com.example.expressstore.services.AuthService
import com.example.expressstore.ui.theme.ExpressStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpressStoreTheme {
                loginScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun loginButton(){
    Button(onClick = {
//        var body = mapOf(
//            "username" to "Elliott41@yahoo.com",
//            "password" to "vasu"
//        )
//
//        ApiClient.apiService.loginUser(body).enqueue(object : retrofit2.Callback<User>{
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                Log.i("Login", response.body()?.token.toString())
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.i("Login", t.message.toString())
//            }
//
//        })
    }) {
        Text(text = "Login")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpressStoreTheme {
        Row {
            Greeting("Vasu")
            loginButton()
        }
    }
}