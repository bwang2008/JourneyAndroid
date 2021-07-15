package com.bwang.journeyandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.bwang.journeyandroid.data.JourneyDTOItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL = "https://jsonplaceholder.typicode.com"
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getJourneyList()  
    }

    private fun getJourneyList() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val journeyData = retrofitBuilder.getJourney()

        journeyData.enqueue(object : Callback<List<JourneyDTOItem>?> {
            override fun onResponse(
                call: Call<List<JourneyDTOItem>?>,
                response: Response<List<JourneyDTOItem>?>
            ) {
                val responseBody = response.body()!!

                val stringBuilder = StringBuilder()
                for (data in responseBody) {
                    with(stringBuilder) {
                        append("journey Id: ${data.id}")
                        append("\n")
                    }
                }

                val theText: TextView = findViewById(R.id.txtId)
                theText.text = stringBuilder
            }

            override fun onFailure(call: Call<List<JourneyDTOItem>?>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }

}