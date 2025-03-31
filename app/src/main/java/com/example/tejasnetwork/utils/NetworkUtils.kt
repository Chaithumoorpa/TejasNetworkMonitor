package com.example.tejasnetwork.utils


import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import android.telephony.CellInfoLte
import android.telephony.CellSignalStrengthLte
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

object NetworkUtils {

    fun getNetworkType(context: Context): String {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork
        val capabilities = cm.getNetworkCapabilities(network)

        return when {
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> "Wi-Fi"
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> "Cellular"
            else -> "No Connection"
        }
    }



    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun getLTEParameters(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cellInfoList = telephonyManager.allCellInfo

        for (cellInfo in cellInfoList) {
            if (cellInfo is CellInfoLte) {
                val cellSignal: CellSignalStrengthLte = cellInfo.cellSignalStrength
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    """
                        RSRP: ${cellSignal.rsrp}
                        RSSI: ${cellSignal.rssi}
                        Number of Channels: N/A
                        Band: ${cellInfo.cellIdentity.bandwidth}
                        TAC-ECI: ${cellInfo.cellIdentity.tac}-${cellInfo.cellIdentity.ci}
                    """.trimIndent()
                } else {
                    TODO("VERSION.SDK_INT < Q")
                }
            }
        }
        return "No LTE Info Available"
    }
}
