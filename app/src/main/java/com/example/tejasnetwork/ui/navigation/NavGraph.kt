package com.example.tejasnetwork.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tejasnetwork.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onItemClick = { screen ->
                navController.navigate(screen)  // ✅ Fix: Navigates correctly
            })
        }

//        composable(Destinations.CellularCalls) { CellularCallsScreen() }
//        composable(Destinations.NetworkSelection) { NetworkSelectionScreen() }
//        composable(Destinations.SpeedTest) { SpeedTestScreen() }
//        composable(Destinations.DeviceInfo) { DeviceInfoScreen() }
//        composable(Destinations.DataUsage) { DataUsageScreen() }
//        composable(Destinations.WifiBehavior) { WifiBehaviorScreen() }
//        composable(Destinations.TrafficProfile) { TrafficProfileScreen() }
//        composable(Destinations.Graph) { GraphScreen() }
//        composable(Destinations.Statistics) { StatisticsScreen() }
//        composable(Destinations.Map) { MapScreen() }
//        composable(Destinations.DataSync) { DataSyncScreen() }
//        composable(Destinations.Privacy) { PrivacyScreen() }
    }
}



