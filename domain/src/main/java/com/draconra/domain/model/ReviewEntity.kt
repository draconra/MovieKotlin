package com.draconra.domain.model

data class ReviewEntity (
        var id: String,
        var author: String,
        var content: String? = null
)