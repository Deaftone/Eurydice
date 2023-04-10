package net.deaftone.eurydice.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "albums",
)
data class Album(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @SerializedName("album_artist")@ColumnInfo(name = "album_artist") val albumArtist: String,
)