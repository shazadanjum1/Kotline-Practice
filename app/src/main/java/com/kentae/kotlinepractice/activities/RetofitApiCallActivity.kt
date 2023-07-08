package com.kentae.kotlinepractice.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.models.QuoteList
import com.kentae.kotlinepractice.models.UserInfo
import com.kentae.kotlinepractice.retrofit.QuotesApi
import com.kentae.kotlinepractice.retrofit.RetrofitHelper
import com.kentae.kotlinepractice.retrofit2.RestApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RetofitApiCallActivity : AppCompatActivity() {

    lateinit var tv : TextView;
    lateinit var progressDialog : ProgressDialog;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retofit_api_call)

        progressDialog = ProgressDialog(this@RetofitApiCallActivity)
        progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Application is loading, please wait")

        val button : Button = findViewById(R.id.fetchBtn)
        tv = findViewById(R.id.tv)

        button.setOnClickListener( object : View.OnClickListener{
            override fun onClick(v: View?) {
                //callApi();
                //addDummyUser();
                getQuotes();
            }
        });
    }

    private fun callApi(){
        progressDialog.show()

        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

        // Get the ConnectivityManager instance
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // launching a new coroutine
        GlobalScope.launch {
            // Check network availability
            val networkInfo = connectivityManager.activeNetworkInfo

            if (networkInfo != null && networkInfo.isConnected) {
                // If internet connection is available
                val result = quotesApi.getQuotes()


                // Checking the results
                Log.d("QuoteList: ", result.body().toString())

                if (result.isSuccessful) {
                    val quoteList: QuoteList? = result.body()

                    runOnUiThread {
                        progressDialog.dismiss()

                        tv.setText(quoteList?.results.toString())
                    }
                } else {
                    // Handle API call failure
                    runOnUiThread {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, "Failed to get quotes", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // No internet connection
                runOnUiThread {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addDummyUser() {
        progressDialog.show()

        val apiService = RestApiService()
        val userInfo = UserInfo(
            userId = null,
            userName = "Alex",
            userEmail = "alex@gmail.com",
            userAge = "32",
            userUid = "164E92FC-D37A-4946-81CB-29DE7EE4B124" )

        apiService.addUser(userInfo) {
            if (it?.userId != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
                runOnUiThread {
                    progressDialog.dismiss()
                    tv.setText(it?.userEmail)
                }
            } else {
                //Timber.d("Error registering new user")
                // No internet connection
                runOnUiThread {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Error registering new user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getQuotes() {
        progressDialog.show()
        val apiService = RestApiService()
        apiService.getQuotes {
            if (it?.results != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
                runOnUiThread {
                    progressDialog.dismiss()
                    tv.setText(it?.results.toString())
                }
            } else {
                //Timber.d("Error registering new user")
                // No internet connection
                runOnUiThread {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}


