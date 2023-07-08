package com.kentae.kotlinepractice.retrofit2

import com.kentae.kotlinepractice.models.QuoteList
import com.kentae.kotlinepractice.models.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>

    @GET("/quotes")
    fun getQuotes() : Call<QuoteList>
}