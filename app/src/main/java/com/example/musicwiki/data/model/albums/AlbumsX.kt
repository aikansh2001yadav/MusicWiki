package com.example.musicwiki.data.model.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlbumsX(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val album: List<Album>
)