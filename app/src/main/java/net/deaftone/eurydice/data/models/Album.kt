package net.deaftone.eurydice.data.models

import com.google.gson.annotations.SerializedName
import net.deaftone.eurydice.data.entities.Album

data class AlbumResponse(
    @SerializedName("data")
    val response: _AlbumResponse
)
data class _AlbumResponse (
    @SerializedName("album")
    val abums: List<Album>,
)
