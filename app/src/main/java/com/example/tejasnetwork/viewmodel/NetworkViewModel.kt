package com.example.tejasnetwork.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import android.net.TrafficStats
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class NetworkViewModel(application: Application) : AndroidViewModel(application) {

    private val connectivityManager =
        application.getSystemService(ConnectivityManager::class.java)

    private val _networkStatus = MutableStateFlow("Checking...")
    val networkStatus = _networkStatus.asStateFlow()

    private val _networkSpeed = MutableStateFlow("Checking...")
    val networkSpeed: StateFlow<String> = _networkSpeed

    init {
        checkCurrentNetworkStatus()
        observeNetworkChanges()
        monitorNetworkSpeed()
    }

    private fun checkCurrentNetworkStatus() {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        _networkStatus.value = if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
            "Online"
        } else {
            "Offline"
        }
    }

    private fun observeNetworkChanges() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _networkStatus.value = "Online"
            }

            override fun onLost(network: Network) {
                _networkStatus.value = "Offline"
            }
        })
    }

    private fun monitorNetworkSpeed() {
        viewModelScope.launch {
            var previousRxBytes = TrafficStats.getTotalRxBytes()
            var previousTxBytes = TrafficStats.getTotalTxBytes()

            while (true) {
                delay(1000) // Update every second

                val currentRxBytes = TrafficStats.getTotalRxBytes()
                val currentTxBytes = TrafficStats.getTotalTxBytes()

                val downloadSpeed = (currentRxBytes - previousRxBytes).toDouble()
                val uploadSpeed = (currentTxBytes - previousTxBytes).toDouble()

                previousRxBytes = currentRxBytes
                previousTxBytes = currentTxBytes

                _networkSpeed.value = "⬇ ${formatSpeed(downloadSpeed)} | ⬆ ${formatSpeed(uploadSpeed)}"
            }
        }
    }

    private fun formatSpeed(bytesPerSecond: Double): String {
        val kbps = bytesPerSecond / 1024
        val mbps = kbps / 1024
        val gbps = mbps / 1024

        return when {
            gbps >= 1 -> String.format("%.2f GB/s", gbps)
            mbps >= 1 -> String.format("%.2f MB/s", mbps)
            kbps >= 1 -> String.format("%.2f KB/s", kbps)
            else -> String.format("%.2f B/s", bytesPerSecond)
        }
    }
}
