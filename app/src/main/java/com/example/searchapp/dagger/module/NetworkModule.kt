package com.example.searchapp.dagger.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideHeaderClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "563492ad6f91700001000001b39c7e66f3d54c45b89389a8ddc4811f"
                )
                .build()
            chain.proceed(request)
        }.addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    fun provideRetrofitClient(headerClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .client(headerClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}