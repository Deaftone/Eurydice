package net.deaftone.data.model.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "artists",
)
data class Artist(
    @PrimaryKey @ColumnInfo(name = "id") val id: String
)
