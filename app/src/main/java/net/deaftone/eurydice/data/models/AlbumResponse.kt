package net.deaftone.eurydice.data.models

import com.squareup.moshi.Json
import net.deaftone.eurydice.data.entities.Album

data class AlbumResponse(
    @Json(name = "data")
    val album: Album
)
