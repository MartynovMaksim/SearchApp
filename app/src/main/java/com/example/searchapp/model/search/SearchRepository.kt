package com.example.searchapp.model.search

import com.example.searchapp.model.PhotosResponse

class SearchRepository (searchStoreRemote: SearchStoreRemote) {
    private val api = searchStoreRemote.searchApi

    suspend fun search(query: String): List<PhotosResponse.Source> {
        return api.getImageList(query).photos
    }
}