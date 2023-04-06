package net.deaftone.eurydice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.deaftone.eurydice.data.entities.Album
import net.deaftone.eurydice.data.entities.Artist
import net.deaftone.eurydice.data.room.AlbumDao
import net.deaftone.eurydice.data.room.ArtistDao

@Database(
entities = [Album::class, Artist::class],
version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
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