package com.example.searchapp.searchrepository

import com.example.searchapp.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
        @GET("v1/search")
        suspend fun getImageList(@Query("query") query: String) : PhotosResponse
}