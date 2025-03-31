package com.example.tejasnetwork.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tejasnetwork.viewmodel.NetworkViewModel
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    private val networkViewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkMonitorScreen(networkViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkMonitorScreen(networkViewModel: NetworkViewModel = viewModel()) {
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val networkSpeed by networkViewModel.networkSpeed.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tejas Network Monitor") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Network Status:", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = networkStatus,
                style = MaterialTheme.typography.headlineLarge,
                color = if (networkStatus == "Online") Color.Green else Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Network Speed:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(networkSpeed, style = MaterialTheme.typography.bodyLarge)
        }

    }
}
