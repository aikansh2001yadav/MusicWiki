package com.example.musicwiki.data.model.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val track: List<Track>
)