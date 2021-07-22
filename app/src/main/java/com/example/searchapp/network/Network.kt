package com.example.searchapp.network

import com.example.searchapp.searchrepository.SearchApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private var retrofit: Retrofit? = null
    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val headerClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "563492ad6f91700001000001b39c7e66f3d54c45b89389a8ddc4811f")
            .build()
        chain.proceed(request)
    }.build()
    private val httpLoggingClient =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    private fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(httpLoggingClient)
                .client(headerClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return requireNotNull(retrofit)
    }

    private const val BASE_URL = "https://api.pexels.com/"
    val searchApi: SearchApi = getClient(BASE_URL).create(SearchApi::class.java)
}