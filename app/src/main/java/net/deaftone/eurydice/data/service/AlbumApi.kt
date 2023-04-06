package net.deaftone.eurydice.data.service

import net.deaftone.eurydice.data.entities.Album
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
    @GET("albums/")
    suspend fun getAllAlbums(): Response<List<Album>>
}