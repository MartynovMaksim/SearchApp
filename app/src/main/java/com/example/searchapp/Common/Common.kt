package com.example.searchapp.Common

import com.example.searchapp.Interface.RetrofitServices
import com.example.searchapp.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://pixabay.com/api/"
    val retrofitServices: RetrofitServices
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}