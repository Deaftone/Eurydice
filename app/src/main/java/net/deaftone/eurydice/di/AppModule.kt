package net.deaftone.eurydice.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.deaftone.eurydice.App
import net.deaftone.eurydice.data.AppDatabase
import net.deaftone.eurydice.data.entities.Album
import net.deaftone.eurydice.data.service.AlbumService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.26:3030")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Provides
    fun provideAlbumService(retrofit: Retrofit): AlbumService = retrofit.create(AlbumService::class.java)

    @Provides
    @Singleton
    fun provideAlbumDao(database: AppDatabase) = database.albumDao()
}