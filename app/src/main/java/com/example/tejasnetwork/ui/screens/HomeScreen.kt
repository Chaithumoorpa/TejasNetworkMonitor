package com.example.tejasnetwork.ui.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*


@Composable
fun HomeScreen(onItemClick: (String) -> Unit) {
    val useCases = listOf(
        "Cellular Calls", "Network Selection", "Speed Test", "Device Info",
        "Data Usage", "WiFi Behavior", "Traffic Profile", "Graph",
        "Statistics", "Map", "Data Sync", "Privacy Settings"
    )

    val icons = listOf(
        Icons.Filled.Call, Icons.Filled.NetworkCell, Icons.Filled.Speed,
        Icons.Filled.PhoneAndroid, Icons.Filled.DataUsage, Icons.Filled.Wifi,
        Icons.Filled.Traffic, Icons.Filled.BarChart, Icons.Filled.Assessment,
        Icons.Filled.Map, Icons.Filled.Sync, Icons.Filled.Security
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Tejas Network Monitor",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(useCases.size) { index ->
                GridItem(title = useCases[index], icon = icons[index]) {
                    onItemClick(useCases[index])
                }
            }
        }
    }
}

@Composable
fun GridItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF007AFF) // Blue accent color
            )
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onItemClick = {})
}
