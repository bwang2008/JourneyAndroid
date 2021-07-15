package com.bwang.journeyandroid

import com.bwang.journeyandroid.data.JourneyDTOItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(value = "posts")
    fun getJourney(): Call<List<JourneyDTOItem>>
}