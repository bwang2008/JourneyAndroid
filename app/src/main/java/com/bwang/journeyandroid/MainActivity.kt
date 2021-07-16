package com.bwang.journeyandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bwang.journeyandroid.data.JourneyDTOItem
import com.bwang.journeyandroid.repository.ApiInterface
import com.bwang.journeyandroid.ui.JourneyListAdaptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com"
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var recyclerListView: RecyclerView
    lateinit var journeyListAdaptor: JourneyListAdaptor
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerListView= findViewById(R.id.recyclerList)
        recyclerListView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerListView.layoutManager = linearLayoutManager

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
                journeyListAdaptor = JourneyListAdaptor(baseContext, responseBody)
                journeyListAdaptor.notifyDataSetChanged()
                recyclerListView.adapter = journeyListAdaptor

//                val stringBuilder = StringBuilder()
//                for (data in responseBody) {
//                    with(stringBuilder) {
//                        append("Journey Id: ${data.id}")
//                        append("\n")
//                    }
//                }
//
//                val theText: TextView = findViewById(R.id.txtId)
//                theText.text = stringBuilder
            }

            override fun onFailure(call: Call<List<JourneyDTOItem>?>, t: Throwable) {
                d(TAG, "onFailure: " + t.message)
            }
        })
    }

}