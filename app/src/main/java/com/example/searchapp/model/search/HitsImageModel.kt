package com.example.searchapp.model

data class HitsResponse(
    val hits: List<Image> = emptyList()
) {
    data class Image (
        var tags: String? = null,
        var previewURL: String? = null,
        var likes: String? = null,
    )
}
