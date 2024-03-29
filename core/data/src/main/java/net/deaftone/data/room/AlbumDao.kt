package net.deaftone.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.deaftone.data.model.album.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Album>)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): LiveData<List<Album>>
}
