package com.kentae.kotlinepractice.retrofit3

import com.kentae.kotlinepractice.models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key : String) : Call<Movies>
}