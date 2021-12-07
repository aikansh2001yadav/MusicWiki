package com.example.musicwiki.data.model.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    @Expose
    val text: String,
    val size: String
)