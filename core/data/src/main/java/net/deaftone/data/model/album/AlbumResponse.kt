package net.deaftone.data.model.album

import com.squareup.moshi.Json

data class AlbumResponse(
    @Json(name = "data")
    val album: Album
)
