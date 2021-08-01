package com.example.searchapp.view.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.model.PhotosResponse
import com.example.searchapp.model.search.SearchRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

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