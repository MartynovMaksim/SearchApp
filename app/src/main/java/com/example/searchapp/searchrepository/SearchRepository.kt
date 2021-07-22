package com.example.searchapp.searchrepository

import android.util.Log
import com.example.searchapp.model.PhotosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository (searchStoreRemote: SearchStoreRemote) {
    private val QUERY = "red flower"
    private val api = searchStoreRemote.searchApi
    fun search(showList:(List<PhotosResponse.Source>) -> Unit) {
            api.getImageList(QUERY).enqueue(object : Callback<PhotosResponse?> {
                override fun onFailure(call: Call<PhotosResponse?>, t: Throwable) {
                    Log.e("SearchRepository","${t.message}")
                }

                override fun onResponse(
                    call: Call<PhotosResponse?>,
                    response: Response<PhotosResponse?>
                ) {
                    Log.e("SearchRepository","$response")
                    if (response.isSuccessful) {
                        val photosResponse = response.body()
                        showList(requireNotNull(photosResponse?.photos))
                    }

                }
            })
    }
}