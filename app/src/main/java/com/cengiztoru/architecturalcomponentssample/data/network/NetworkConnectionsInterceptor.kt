package com.cengiztoru.architecturalcomponentssample.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.cengiztoru.architecturalcomponentssample.util.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionsInterceptor(context: Context) : Interceptor {

    private val applicationContext = context

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!checkIsNetworkAvailable()) {
            throw NoInternetExceptions("Make sure you have an active data connection")
        }

        return chain.proceed(chain.request())
    }

    private fun checkIsNetworkAvailable(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return isInternetAvailableNEW(applicationContext)
        } else {
            return isInternetAvailableOLD(applicationContext)
        }
    }

    private fun isInternetAvailableOLD(applicationContext: Context): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //todo Deprecated
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isInternetAvailableNEW(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

}