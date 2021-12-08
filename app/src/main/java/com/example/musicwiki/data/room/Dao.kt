package com.example.musicwiki.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.data.model.tracks.Track

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag)

    @Query("SELECT * FROM tag WHERE tag_name = :tagName")
    fun getTag(tagName: String): Tag

    @Query("SELECT * FROM artist WHERE tag = :tagName")
    fun getArtistList(tagName: String): List<Artist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: Artist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Query("SELECT * FROM album WHERE tagName = :tagName")
    fun getAlbumList(tagName: String): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)

    @Query("SELECT * FROM track WHERE tagName = :tagName")
    fun getTrackList(tagName: String): List<Track>
}