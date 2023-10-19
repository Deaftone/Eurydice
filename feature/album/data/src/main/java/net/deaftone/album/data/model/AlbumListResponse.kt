package net.deaftone.album.data.model

import com.squareup.moshi.Json

data class AlbumListResponse(
    @Json(name = "data")
    val albums: List<Album>
)
