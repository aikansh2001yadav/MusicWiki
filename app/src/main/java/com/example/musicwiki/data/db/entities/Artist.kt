package com.example.musicwiki.data.db.entities

import androidx.room.Entity
import androidx.room.Ignore
import com.example.musicwiki.data.model.common.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Entity class that stores information about artist
 */
@Entity(primaryKeys = ["tag", "name"])
data class Artist(
    val tag : String,
    @SerializedName("image")
    @Expose
    val image: List<Image?>,
    @SerializedName("name")
    @Expose
    val name: String
){
    @Ignore
    constructor(image : List<Image>, name:String) : this("", image, name)
}