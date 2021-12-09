package com.example.musicwiki.data.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Stores image's information
 */
data class Image(
    @SerializedName("#text")
    @Expose
    val text: String,
    val size: String
)