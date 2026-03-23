package com.t4lon.sportifield.ui.features.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationScreen(viewModel: AuthenticationViewModel) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AuthenticationContract.Effect.NavigateToHome -> {
                    // Navigate to home screen
                }
                is AuthenticationContract.Effect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = state.email,
            onValueChange = { viewModel.handleIntent(AuthenticationContract.Intent.EmailChanged(it)) },
            label = { Text("Email") }
        )

        TextField(
            value = state.password,
            onValueChange = { viewModel.handleIntent(AuthenticationContract.Intent.PasswordChanged(it)) },
            label = { Text("Password") }
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = { viewModel.handleIntent(AuthenticationContract.Intent.LoginClicked) }) {
                Text("Login")
            }
        }

        state.error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}