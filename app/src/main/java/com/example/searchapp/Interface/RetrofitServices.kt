package com.example.searchapp.Interface

import com.example.searchapp.Model.HitsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
        @GET("image")
        fun getImageList(@Query("key") key: String, @Query("q") query: String): Call<HitsResponse>
}