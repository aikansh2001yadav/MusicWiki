package com.example.musicwiki.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.musicwiki.utils.UtilExceptions
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val context = context.applicationContext

    /**
     * If there is no problem in connectivity and internet is not available, then process request
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionOn()) {
            throw UtilExceptions.NoConnectivityException()
        } else if (!isInternetAvailable()) {
            throw UtilExceptions.NoInternetException()
        } else {
            chain.proceed(chain.request())
        }
    }

    /**
     * Checks whether connection is working fine or not
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isConnectionOn(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as
                    ConnectivityManager


        val network = connectivityManager.activeNetwork
        val connection =
            connectivityManager.getNetworkCapabilities(network)

        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))

    }

    /**
     * Checks internet connectivity
     */
    private fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockAddress = InetSocketAddress("8.8.8.8", 53)

            sock.connect(sockAddress, timeoutMs)
            sock.close()

            true
        } catch (e: IOException) {
            false
        }
    }
}