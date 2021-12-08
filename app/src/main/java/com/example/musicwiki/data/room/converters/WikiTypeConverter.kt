package com.example.musicwiki.data.room.converters

import androidx.room.TypeConverter
import com.example.musicwiki.data.model.genreInfo.Wiki
import org.json.JSONObject

class WikiTypeConverter {

    @TypeConverter
    fun fromWiki(wiki: Wiki) : String{
        return JSONObject().apply {
            put("content", wiki.content)
            put("summary", wiki.summary)
        }.toString()
    }

    @TypeConverter
    fun toWiki(wiki: String) : Wiki {
        val json = JSONObject(wiki)
        return Wiki(
            json.getString("content"),
            json.getString("summary")
        )
    }
}