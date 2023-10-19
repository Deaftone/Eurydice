package net.deaftone.album.data.model

import com.squareup.moshi.Json

data class Album(
    @Json(name = "id")val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "album_artist") val albumArtist: String?,
    @Json(name = "songs") val songs: List<Song>?,
)
