package com.example.searchapp.presenter

import com.example.searchapp.model.HitsResponse
import com.example.searchapp.searchrepository.SearchRepository

class SearchPresenter(private val searchRepository: SearchRepository) {
    fun show(showList: (List<HitsResponse.Image>) -> Unit) {
        searchRepository.search(showList)
    }
}