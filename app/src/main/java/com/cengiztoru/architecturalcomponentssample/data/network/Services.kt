package com.cengiztoru.architecturalcomponentssample.data.network

import com.cengiztoru.architecturalcomponentssample.data.network.responses.AuthResponse
import com.cengiztoru.architecturalcomponentssample.data.network.responses.QuotesResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Services {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>


    @GET("quotes")
    suspend fun getQuotes(): Response<QuotesResponse>
}