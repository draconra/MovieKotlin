package com.draconra.domain.model

data class MovieDetailsEntity(
        var belongsToCollection: Any? = null,
        var budget: Int? = null,
        var homepage: String? = null,
        var imdbId: String? = null,
        var overview: String? = null,
        var revenue: Int? = null,
        var runtime: Int? = null,
        var status: String? = null,
        var tagline: String? = null,
        var reviewEntities: List<ReviewEntity>? = null,
        var videoEntities: List<VideoEntity>? = null,
        var genres: List<GenreEntity>? = null
)