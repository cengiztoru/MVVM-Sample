package com.cengiztoru.architecturalcomponentssample.data.network.responses

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
) {
    data class Quote(
        val author: String,
        val created_at: Any,
        val id: Int,
        val quote: String,
        val thumbnail: String,
        val updated_at: Any
    )
}