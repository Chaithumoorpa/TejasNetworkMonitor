package com.example.tejasnetwork.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NetworkViewModel(application: Application) : AndroidViewModel(application) {

    private val connectivityManager =
        application.getSystemService(ConnectivityManager::class.java)

    private val _networkStatus = MutableStateFlow("Checking...")
    val networkStatus = _networkStatus.asStateFlow()

    init {
        checkCurrentNetworkStatus()
        observeNetworkChanges()
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
}
