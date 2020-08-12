package com.cengiztoru.architecturalcomponentssample.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MyApi {
    operator fun invoke(networkConnectionsInterceptor: NetworkConnectionsInterceptor): Services {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkConnectionsInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()


        return Retrofit.Builder()
            .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Services::class.java)
    }
}