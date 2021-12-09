package com.example.musicwiki.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicwiki.data.room.entities.*

@Dao
interface Dao {
    /**
     * Stores tag item in the room database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag)

    /**
     * Returns tag item
     */
    @Query("SELECT * FROM tag WHERE tag_name = :tagName")
    fun getTag(tagName: String): Tag

    /**
     * Return list of artists
     */
    @Query("SELECT * FROM artist WHERE tag = :tagName")
    fun getArtistList(tagName: String): List<Artist>

    /**
     * Stores artist item in the room database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: Artist)

    /**
     * Stores album item in the room database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    /**
     * Returns list of albums
     */
    @Query("SELECT * FROM album WHERE tagName = :tagName")
    fun getAlbumList(tagName: String): List<Album>

    /**
     * Stores track item in the room database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)

    /**
     * Returns list of tracks
     */
    @Query("SELECT * FROM track WHERE tagName = :tagName")
    fun getTrackList(tagName: String): List<Track>

    /**
     * Stores list of genre items in the room database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreList(genreList: List<Genre>)

    /**
     * Returns list of genre
     */
    @Query("SELECT * FROM genre")
    fun getGenreList(): List<Genre>
}