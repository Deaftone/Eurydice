package net.deaftone.data.model.album

import com.squareup.moshi.Json

data class AlbumListResponse(
    @Json(name = "data")
    val albums: List<Album>
)
