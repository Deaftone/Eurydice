package net.deaftone.data.model.album

import com.squareup.moshi.Json
import net.deaftone.data.model.song.Song

data class Album(
    @Json(name = "id")val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "album_artist") val albumArtist: String?,
    @Json(name = "songs") val songs: List<Song>?,
)
