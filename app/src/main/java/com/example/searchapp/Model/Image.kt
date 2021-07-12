package com.example.searchapp.Model

data class HitsResponse(
    val hits: List<Image> = emptyList()
)

data class Image (
    var id: String? = null,
    var pageURL: String? = null,
    var type: String? = null,
    var tags: String? = null,
    var previewURL: String? = null,
    var previewWidth: String? = null,
    var previewHeight: String? = null,
    var webformatURL: String? = null,
    var webformatWidth: String? = null,
    var webformatHeight: String? = null,
    var largeImageURL: String? = null,
    var imageWidth: String? = null,
    var imageHeight: String? = null,
    var imageSize: String? = null,
    var views: String? = null,
    var downloads: String? = null,
    var collections: String? = null,
    var likes: String? = null,
    var comments: String? = null,
    var user_id: String? = null,
    var user: String? = null,
    var userImageURL: String? = null,
        )