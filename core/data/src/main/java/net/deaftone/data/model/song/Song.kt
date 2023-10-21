package net.deaftone.data.model.song

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Song(
    @Json(name = "id") @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @Json(name = "title") @ColumnInfo(name = "title") val title: String,
)
