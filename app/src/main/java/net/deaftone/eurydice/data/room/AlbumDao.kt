package net.deaftone.eurydice.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import net.deaftone.eurydice.data.entities.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Album>)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): LiveData<List<Album>>
}