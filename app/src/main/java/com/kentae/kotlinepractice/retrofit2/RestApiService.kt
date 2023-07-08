package com.kentae.kotlinepractice.retrofit2

import com.kentae.kotlinepractice.models.QuoteList
import com.kentae.kotlinepractice.models.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RestApiService {
    fun addUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UserInfo>, response: Response<UserInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun getQuotes(onResult: (QuoteList?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getQuotes().enqueue(
            object : Callback<QuoteList> {
                override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<QuoteList>, response: Response<QuoteList>) {
                    val quoteList = response.body()
                    onResult(quoteList)
                }
            }
        )
    }
}