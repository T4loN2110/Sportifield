package com.t4lon.sportifield.ui.features.email_verification

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.t4lon.sportifield.R
import kotlinx.coroutines.launch

@Composable
fun EmailVerificationScreen(
    viewModel: EmailVerificationViewModel, 
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        launch {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is EmailVerificationContract.Effect.ShowToast -> {
                        Toast.makeText(
                            context,
                            effect.message.asString(context),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is EmailVerificationContract.Effect.NavigateToHome -> {
                        navController.navigate("home") {
                            popUpTo("email_verification") { inclusive = true }
                        }
                    }
                }
            }
        }

        viewModel.handleIntent(EmailVerificationContract.Intent.StartEmailVerification)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text (
                text = stringResource(R.string.email_verification_subtitle),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}