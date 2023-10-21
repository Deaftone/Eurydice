package net.deaftone.data.service.album

import net.deaftone.data.model.album.AlbumListResponse
import net.deaftone.data.model.album.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAllAlbums(): Response<AlbumListResponse>
    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: String): Response<AlbumResponse>
}
