package com.example.musicwiki.data.model.genreInfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tag_name")
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)