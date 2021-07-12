package com.example.searchapp.searchrepository

import com.example.searchapp.model.HitsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
        @GET("api")
        fun getImageList(@Query("key") key: String, @Query("q") query: String): Call<HitsResponse>
}