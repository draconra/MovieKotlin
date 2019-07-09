package com.draconra.domain.model

data class VideoEntity(
        var id: String,
        var name: String,
        var youtubeKey: String? = null,
        var url: String? = null) {

    companion object {
        const val SOURCE_YOUTUBE = "YouTube"
        const val TYPE_TRAILER = "Trailer"
    }
}