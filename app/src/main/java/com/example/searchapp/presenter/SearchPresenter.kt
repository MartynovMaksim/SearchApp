package com.example.searchapp.presenter

import com.example.searchapp.SearchAdapter
import com.example.searchapp.model.HitsResponse
import com.example.searchapp.searchrepository.SearchRepository
import com.example.searchapp.searchrepository.SearchStoreRemote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(presenterAdapter: SearchAdapter) {
    private val adapter = presenterAdapter
    fun show() {
        val searchRepository = SearchRepository(SearchStoreRemote(), adapter)
        searchRepository.getApiResponse()
    }
}