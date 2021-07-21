package com.example.searchapp.searchrepository

import com.example.searchapp.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {
        @Headers("Authorization: 563492ad6f91700001000001b39c7e66f3d54c45b89389a8ddc4811f")
        @GET("v1/search")
        fun getImageList(@Query("query") query: String) : Call<PhotosResponse>
}