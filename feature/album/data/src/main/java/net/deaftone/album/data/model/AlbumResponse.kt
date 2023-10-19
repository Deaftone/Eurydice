package net.deaftone.album.data.model

import com.squareup.moshi.Json

data class AlbumResponse(
    @Json(name = "data")
    val album: Album
)
