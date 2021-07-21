package com.example.searchapp.model

data class PhotosResponse(
    val photos: List<Source> = emptyList()
) {
    data class Source(
        var photographer: String? = null,
        val src: List<Image> = emptyList()
    ) {
        data class Image(
            var tiny: String? = null
        )
    }
}
