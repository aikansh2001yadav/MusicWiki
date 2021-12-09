package com.example.musicwiki.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class that stores information about genre
 */
@Entity(tableName = "genre")
data class Genre(
    val count: Int,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "genre_name")
    val name: String,
    val reach: Int
)
