package com.example.searchapp.model.search

import com.example.searchapp.model.search.data.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
        @GET("v1/search")
        suspend fun getImageList(@Query("query") query: String) : PhotosResponse
}