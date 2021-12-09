package com.example.musicwiki.data.model.genreItems

import com.example.musicwiki.data.db.entities.Genre
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Stores list of genre
 */
data class TogGenreItems(
    @SerializedName("tag")
    @Expose
    val genre: List<Genre>
)