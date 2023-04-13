package net.deaftone.eurydice.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Album(
    @Json(name = "id")val id: String,
    @Json(name = "name")  val name: String,
    @Json(name = "album_artist")  val albumArtist: String?,
    @Json(name = "songs")  val songs: List<Song>?,
)