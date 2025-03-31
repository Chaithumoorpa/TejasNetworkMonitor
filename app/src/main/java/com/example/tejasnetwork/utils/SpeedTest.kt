package com.example.tejasnetwork.utils


import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlin.system.measureTimeMillis

object SpeedTest {

    fun performSpeedTest(context: Context, callback: (String) -> Unit) {
        val url = "https://www.speedtest.net/" // Replace with a dedicated speed test server
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)

        val downloadTime = measureTimeMillis {
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { _ ->
                    callback("Download Speed: ${1000 / (downloadTime / 1024.0)} KB/s")
                },
                Response.ErrorListener { error ->
                    callback("Error: ${error.message}")
                })

            requestQueue.add(stringRequest)
        }
    }
}
