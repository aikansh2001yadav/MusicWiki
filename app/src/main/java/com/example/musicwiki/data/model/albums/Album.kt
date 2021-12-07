package com.example.musicwiki.data.model.albums

data class Album(
    val @attr: AttrX,
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)