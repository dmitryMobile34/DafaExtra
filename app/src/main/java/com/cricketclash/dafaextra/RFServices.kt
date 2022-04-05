package com.cricketclash.dafaextra

import com.cricketclash.dafaextra.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("v2/top-headlines?country=in&category=sports&apiKey=51269123bb0b4b82bbfb73559fe2357f")
    fun getNews(@Query("q") q: String): Call<News>
}