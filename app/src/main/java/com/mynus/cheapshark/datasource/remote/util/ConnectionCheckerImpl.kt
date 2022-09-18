package com.mynus.cheapshark.datasource.remote.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.mynus.domain.util.ConnectionChecker
import javax.inject.Inject

class ConnectionCheckerImpl @Inject constructor(
    private val ctx: Context
): ConnectionChecker {
    override fun hasConnection(): Boolean {
        val cmg = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            cmg.getNetworkCapabilities(cmg.activeNetwork)?.also { networkCapabilities ->
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
        } else {
            return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
        }
        return false
    }
}