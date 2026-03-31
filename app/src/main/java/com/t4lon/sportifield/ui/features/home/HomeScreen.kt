package com.t4lon.sportifield.ui.features.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.t4lon.sportifield.ui.features.home.components.CategoryRow
import com.t4lon.sportifield.ui.features.home.components.PromoBanner
import com.t4lon.sportifield.ui.features.home.components.SearchBarSection
import com.t4lon.sportifield.ui.features.home.components.SectionTitle
import com.t4lon.sportifield.ui.features.home.components.SportFieldCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: androidx.navigation.NavController? = null) {
    val localNavController = rememberNavController()
    val actualNavController = navController ?: localNavController
    val context = LocalContext.current

    // Handle side effects
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.Effect.ShowToast -> {
                    Toast.makeText(
                        context,
                        effect.message.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Sportifield",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                SearchBarSection()
            }

            item {
                SectionTitle("Interest Categories")
                Spacer(modifier = Modifier.height(12.dp))
                CategoryRow()
            }

            item {
                PromoBanner()
            }
            item {
                SectionTitle("Recommended Fields")
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(3) {
                SportFieldCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}







