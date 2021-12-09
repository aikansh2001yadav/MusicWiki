package com.example.musicwiki.data.room.entities

import androidx.room.Entity
import androidx.room.Ignore
import com.example.musicwiki.data.model.common.Artist
import com.example.musicwiki.data.model.common.Image

/**
 * Entity class that stores information about track
 */
@Entity(primaryKeys = ["tagName", "name"])
data class Track(
    val tagName: String,
    val artist: Artist,
    val image: List<Image>,
    val name: String,
) {
    @Ignore
    constructor(artist: Artist, image: List<Image>, name: String) : this("", artist, image, name)
}