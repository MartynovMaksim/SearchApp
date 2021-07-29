package com.example.searchapp.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.MainApp
import com.example.searchapp.model.PhotosResponse
import com.example.searchapp.network.Network
import com.example.searchapp.searchrepository.SearchRepository
import com.example.searchapp.searchrepository.SearchStoreRemote
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel : ViewModel() {

    @Inject
    lateinit var searchRepository: SearchRepository

    private val searchResultSharedFlow: MutableSharedFlow<List<PhotosResponse.Source>> =
        MutableSharedFlow()
    val searchResultFlow: SharedFlow<List<PhotosResponse.Source>> =
        searchResultSharedFlow.asSharedFlow()

    init {
        MainApp.instance.appComponent.inject(this)
    }

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