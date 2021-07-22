package com.example.searchapp.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.model.PhotosResponse
import com.example.searchapp.network.Network
import com.example.searchapp.searchrepository.SearchRepository
import com.example.searchapp.searchrepository.SearchStoreRemote
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchStoreRemote: SearchStoreRemote = SearchStoreRemote(Network)
    private val searchRepository: SearchRepository = SearchRepository(searchStoreRemote)

    private val searchResultSharedFlow: MutableSharedFlow<List<PhotosResponse.Source>> =
        MutableSharedFlow()
    val searchResultFlow: SharedFlow<List<PhotosResponse.Source>> =
        searchResultSharedFlow.asSharedFlow()

    fun search(query: String) {
        viewModelScope.launch {
            searchResultSharedFlow.emit(
                try {
                    searchRepository.search(query)
                } catch (exc: Exception) {
                    Log.e("SearchViewModel", exc.message.orEmpty())
                    emptyList()
                }
            )
        }
    }

}