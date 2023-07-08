package com.kentae.kotlinepractice.retrofit

import com.kentae.kotlinepractice.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}