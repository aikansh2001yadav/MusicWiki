package com.example.musicwiki.data.room.converters

import androidx.room.TypeConverter
import com.example.musicwiki.data.model.common.Artist
import org.json.JSONObject

/**
 * Type converter for Artist model class
 */
class ArtistTypeConverter {

    @TypeConverter
    fun fromArtist(artist : Artist) : String{
        return JSONObject().apply {
            put("name", artist.name)
            put("url", artist.url)
        }.toString()
    }

    @TypeConverter
    fun toArtist(artist: String) : Artist {
        val json = JSONObject(artist)
        return Artist(
            json.getString("name"),
            json.getString("url")
        )
    }
}