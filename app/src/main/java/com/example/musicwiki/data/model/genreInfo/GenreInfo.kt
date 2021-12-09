package com.example.musicwiki.data.model.genreInfo

import com.example.musicwiki.data.db.entities.Tag

/**
 * Stores information about a particular genre in the form of tag
 */
data class GenreInfo(
    val tag: Tag?
)