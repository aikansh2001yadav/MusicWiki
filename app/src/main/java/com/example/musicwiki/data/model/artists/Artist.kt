package com.example.musicwiki.data.model.artists

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.musicwiki.data.model.commonModels.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Artist(
    val tag : String?,
    @SerializedName("image")
    @Expose
    val image: List<Image?>,
    @SerializedName("name")
    @Expose
    val name: String
){
    @Ignore
    constructor(image : List<Image>, name:String) : this("", image, name)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "artist_id")
    var id:Long = 0
}