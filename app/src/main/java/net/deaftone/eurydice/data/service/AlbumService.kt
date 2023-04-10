package net.deaftone.eurydice.data.service

import net.deaftone.eurydice.data.entities.Album
import net.deaftone.eurydice.data.models.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun getAllAlbums(): Response<List<Album>>
}