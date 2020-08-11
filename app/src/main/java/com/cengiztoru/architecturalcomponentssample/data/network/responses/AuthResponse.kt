package com.cengiztoru.architecturalcomponentssample.data.network.responses

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
) {
    //todo change here to DAO class
    data class User(
        val created_at: String,
        val email: String,
        val email_verified_at: String,
        val id: Int,
        val name: String,
        val updated_at: String
    )
}