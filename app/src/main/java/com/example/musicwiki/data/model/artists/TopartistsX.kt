package com.example.musicwiki.data.model.artists

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopartistsX(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val artist: List<Artist>
)