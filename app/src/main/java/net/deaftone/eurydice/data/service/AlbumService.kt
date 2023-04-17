package net.deaftone.eurydice.data.service

import net.deaftone.eurydice.data.models.AlbumListResponse
import net.deaftone.eurydice.data.models.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAllAlbums(): Response<AlbumListResponse>
    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: String): Response<AlbumResponse>
}
