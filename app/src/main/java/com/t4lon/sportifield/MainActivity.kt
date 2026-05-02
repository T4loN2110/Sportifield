package com.t4lon.sportifield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

import com.t4lon.sportifield.ui.theme.AppTheme
import com.t4lon.sportifield.ui.features.authentication.login.LoginScreen
import com.t4lon.sportifield.ui.features.authentication.register.RegisterScreen
import com.t4lon.sportifield.ui.features.authentication.email_verification.EmailVerificationScreen
import com.t4lon.sportifield.ui.features.authentication.register.RegisterViewModel
import com.t4lon.sportifield.ui.features.home.HomeScreen

@AndroidEntryPoint
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
                            RegisterScreen(
                                viewModel = hiltViewModel<RegisterViewModel>(),
                                navController = navController
                            )
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