package net.deaftone.eurydice.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.deaftone.eurydice.data.entities.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Artist>)

    @Query("SELECT * FROM artists")
    fun getAllAlbums(): LiveData<List<Artist>>
}