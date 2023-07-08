package com.kentae.kotlinepractice.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kentae.kotlinepractice.models.Movies
import com.kentae.kotlinepractice.models.Result
import com.kentae.kotlinepractice.retrofit3.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private var movieLiveData = MutableLiveData<List<Result>>()
//69d66957eebff9666ea46bd464773cf0

    fun getPopularMovies( key : String) {
        RetrofitInstance.api.getPopularMovies(key)
            .enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.body()!=null){
                        movieLiveData.value = response.body()!!.results
                    }
                    else{
                        return
                    }
                }
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                }
            })
    }

    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }
}