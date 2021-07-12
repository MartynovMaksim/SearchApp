package com.example.searchapp.searchrepository

import com.example.searchapp.SearchAdapter
import com.example.searchapp.model.HitsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(private val searchApi: SearchApi) {
    private val KEY = "22385290-8633bd548612ec6195b902710"
    private val QUERY = "red flower"
    lateinit var adapter: SearchAdapter

    private val abc = seachApi.getImageList(KEY,QUERY).enqueue(object: Callback<HitsResponse?> {
        override fun onFailure(call: Call<HitsResponse?>, t: Throwable) {

        }

        override fun onResponse(
            call: Call<HitsResponse?>,
            response: Response<HitsResponse?>
        ) {
            if (response.isSuccessful) {
                val hitsResponse = response.body()
                adapter.setImageList(requireNotNull(hitsResponse?.hits))
            }

        }
    })
}