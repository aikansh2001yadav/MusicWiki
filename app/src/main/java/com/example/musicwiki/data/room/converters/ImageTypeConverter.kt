package com.example.musicwiki.data.room.converters

import androidx.room.TypeConverter
import com.example.musicwiki.data.model.commonModels.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun imageToString(foodItems: List<Image>): String {
        return gson.toJson(foodItems)
    }

    @TypeConverter
    fun stringToImage(data: String): List<Image> {
        val listType = object : TypeToken<List<Image>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}