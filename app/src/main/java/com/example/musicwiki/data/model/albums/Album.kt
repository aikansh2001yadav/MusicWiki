package com.example.musicwiki.data.model.albums

import com.example.musicwiki.data.model.Image

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val name: String,
)