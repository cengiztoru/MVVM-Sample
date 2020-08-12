package com.cengiztoru.architecturalcomponentssample.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.cengiztoru.architecturalcomponentssample.util.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionsInterceptor(context: Context) : Interceptor {

    private val applicationContext = context

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) {
            throw NoInternetExceptions("Make sure you have an active data connection")
        }
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //todo Deprecated
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}