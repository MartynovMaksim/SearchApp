package com.example.searchapp.presenter

import com.example.searchapp.model.PhotosResponse
import com.example.searchapp.searchrepository.SearchRepository

class SearchPresenter(private val searchRepository: SearchRepository) {
    fun show(showList: (List<PhotosResponse.Source>) -> Unit) {
        searchRepository.search(showList)
    }
}