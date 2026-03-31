package com.t4lon.sportifield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.t4lon.sportifield.ui.theme.AppTheme
import com.t4lon.sportifield.ui.features.login.LoginScreen
import com.t4lon.sportifield.ui.features.register.RegisterScreen
import com.t4lon.sportifield.ui.features.email_verification.EmailVerificationScreen
import com.t4lon.sportifield.ui.features.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(viewModel = viewModel(), navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(viewModel = viewModel(), navController = navController)
                        }
                        composable("email_verification") {
                            EmailVerificationScreen(viewModel = viewModel(), navController = navController)
                        }
                        composable("home") {
                            HomeScreen(viewModel = viewModel(), navController = navController)
                        }
                    }
                }
            }
        }
    }
}