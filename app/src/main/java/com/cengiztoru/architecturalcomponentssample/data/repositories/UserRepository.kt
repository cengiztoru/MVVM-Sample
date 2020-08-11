package com.cengiztoru.architecturalcomponentssample.data.repositories

import com.cengiztoru.architecturalcomponentssample.data.network.SafeApiRequest
import com.cengiztoru.architecturalcomponentssample.data.network.Services
import com.cengiztoru.architecturalcomponentssample.data.network.responses.AuthResponse

class UserRepository(
    private val api: Services
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }


    //BEFORE HANDLING API EXCEPTIONS
//
//    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
//        return MyApi().userLogin(email, password)
//    }


    //BEFORE COROUTINES WE USE THIS. IT CLEAN OUR CODE
//    fun userLogin(email: String, password: String): Response<AuthResponse> {
//
//        val loginResponse = MutableLiveData<String>()
//
//        //todo DI
//        MyApi().userLogin(email, password).enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                loginResponse.value = t.message
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful) {
//                    loginResponse.value = response.body()?.string()
//                } else {
//                    loginResponse.value = response.errorBody().toString()
//                }
//
//            }
//        })
//
//        return loginResponse
//
//    }

}

