package net.deaftone.eurydice.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "albums",
)
data class Album(
    @PrimaryKey @ColumnInfo(name = "id") val id: String
)