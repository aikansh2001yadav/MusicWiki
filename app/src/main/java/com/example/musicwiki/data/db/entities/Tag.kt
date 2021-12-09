package com.example.musicwiki.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicwiki.data.model.genreInfo.Wiki

/**
 * Entity class that stores information about tag
 */
@Entity
data class Tag(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tag_name")
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)