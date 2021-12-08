package com.example.musicwiki.data.model.tracks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.musicwiki.data.model.commonModels.Artist
import com.example.musicwiki.data.model.commonModels.Image

@Entity
data class Track(
    val tagName: String?,
    val artist: Artist,
    val image: List<Image>,
    val name: String,
) {
    @Ignore
    constructor(artist: Artist, image: List<Image>, name: String) : this("", artist, image, name)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "track_id")
    var id:Long = 0
}