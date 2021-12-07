package com.example.musicwiki.data.model.tracks

import com.example.musicwiki.data.model.Image

data class Track(
    val artist: Artist,
    val image: List<Image>,
    val name: String,
)