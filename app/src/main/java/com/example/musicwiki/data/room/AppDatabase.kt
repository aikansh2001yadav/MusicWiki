package com.example.musicwiki.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.data.model.tracks.Track
import com.example.musicwiki.data.room.converters.ArtistTypeConverter
import com.example.musicwiki.data.room.converters.ImageTypeConverter
import com.example.musicwiki.data.room.converters.WikiTypeConverter

@TypeConverters(ImageTypeConverter::class, ArtistTypeConverter::class, WikiTypeConverter::class)
@Database(entities = [Tag::class, Artist::class, Album::class, Track::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "offline-database"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}