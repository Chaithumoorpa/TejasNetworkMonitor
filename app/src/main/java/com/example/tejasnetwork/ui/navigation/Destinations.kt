package com.example.tejasnetwork.ui.navigation


object Destinations {
    const val Home = "home"
    const val CellularCalls = "cellular_calls"
    const val NetworkSelection = "network_selection"
    const val SpeedTest = "speed_test"
    const val DeviceInfo = "device_info"
    const val DataUsage = "data_usage"
    const val WifiBehavior = "wifi_behavior"
    const val TrafficProfile = "traffic_profile"
    const val Graph = "graph"
    const val Statistics = "statistics"
    const val Map = "map"
    const val DataSync = "data_sync"
    const val Privacy = "privacy"

    fun getDestination(title: String): String {
        return when (title) {
            "Cellular Calls" -> CellularCalls
            "Network Selection" -> NetworkSelection
            "Speed Test" -> SpeedTest
            "Device Info" -> DeviceInfo
            "Data Usage" -> DataUsage
            "WiFi Behavior" -> WifiBehavior
            "Traffic Profile" -> TrafficProfile
            "Graph" -> Graph
            "Statistics" -> Statistics
            "Map" -> Map
            "Data Sync" -> DataSync
            "Privacy Settings" -> Privacy
            else -> Home
        }
    }
}
