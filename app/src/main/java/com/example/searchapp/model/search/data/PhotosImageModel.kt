package com.example.searchapp.model.search.data

data class PhotosResponse(
    val photos: List<Source> = emptyList()
) {
    data class Source(
        var id: Long,
        var photographer: String? = null,
        val src: Image? = null
    ) {
        data class Image(
            var original: String? = null,
            var tiny: String? = null
        )
    }
}
