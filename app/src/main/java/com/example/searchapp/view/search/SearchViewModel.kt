package com.example.searchapp.view.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.model.search.data.PhotosResponse
import com.example.searchapp.model.search.SearchRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val querySharedFlow: MutableSharedFlow<String> = MutableSharedFlow(1)
    private val searchResultSharedFlow: MutableSharedFlow<List<PhotosResponse.Source>> =
        MutableSharedFlow(replay = 1)
    val searchResultFlow: SharedFlow<List<PhotosResponse.Source>> =
        searchResultSharedFlow.asSharedFlow()

    init {
        viewModelScope.launch { search() }
    }

    fun onQueryChange(query: String) {
        querySharedFlow.tryEmit(query)
    }

    private suspend fun search() {
        querySharedFlow
            .debounce(300)
            .mapLatest { query ->
                try {
                    searchRepository.search(query)
                } catch (exc: Exception) {
                    Log.e("SearchViewModel", exc.message.orEmpty())
                    emptyList()
                }
            }
            .collect { photos ->
                searchResultSharedFlow.emit(photos)
            }
    }
}