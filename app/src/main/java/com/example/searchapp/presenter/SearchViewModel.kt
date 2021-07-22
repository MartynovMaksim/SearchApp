package com.example.searchapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchapp.model.PhotosResponse
import com.example.searchapp.network.Network
import com.example.searchapp.searchrepository.SearchRepository
import com.example.searchapp.searchrepository.SearchStoreRemote

class SearchViewModel : ViewModel() {
    private val searchStoreRemote = SearchStoreRemote(Network)
    private val searchRepository = SearchRepository(searchStoreRemote)

    fun search(onSuccess: (List<PhotosResponse.Source>) -> Unit) {
            searchRepository.search(onSuccess)
    }

}