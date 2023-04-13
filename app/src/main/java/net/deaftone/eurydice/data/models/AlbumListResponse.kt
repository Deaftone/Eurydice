package net.deaftone.eurydice.data.models

import com.squareup.moshi.Json
import net.deaftone.eurydice.data.entities.Album

data class AlbumListResponse(
    @Json(name = "data")
    val albums: List<Album>
)
