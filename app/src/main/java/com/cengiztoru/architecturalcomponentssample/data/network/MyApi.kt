package com.cengiztoru.architecturalcomponentssample.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyApi {
    operator fun invoke(): Services {
        return Retrofit.Builder()
            .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Services::class.java)
    }
}