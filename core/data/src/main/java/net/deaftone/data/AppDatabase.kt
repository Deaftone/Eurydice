package net.deaftone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.deaftone.data.model.artist.Artist
import net.deaftone.data.room.ArtistDao
@Database(
    entities = [Artist::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    // abstract fun albumDao(): AlbumDao
    abstract fun artistDao(): ArtistDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "eurydice")
                .fallbackToDestructiveMigration()
                .build()
    }
}
