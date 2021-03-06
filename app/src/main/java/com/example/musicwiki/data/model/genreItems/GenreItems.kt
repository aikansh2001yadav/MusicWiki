package com.example.musicwiki.data.model.genreItems

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Stores model class for topGenreItems
 */
data class GenreItems(
    @SerializedName("toptags")
    @Expose
    val togGenreItems: TogGenreItems?
)