package com.example.musicwiki.data.model.albums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("@attr")
    @Expose
    val attr: AttrX,
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)