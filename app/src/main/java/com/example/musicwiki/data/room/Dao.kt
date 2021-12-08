package com.example.musicwiki.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.data.model.tracks.Track

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtistList(artists : List<Artist>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist:Artist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album : Album)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)
}