package com.example.musicwiki.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.data.model.albums.Albums
import com.example.musicwiki.data.model.albums.AlbumsX
import com.example.musicwiki.data.model.artists.TopArtists
import com.example.musicwiki.data.model.artists.TopartistsX
import com.example.musicwiki.data.model.genreInfo.GenreInfo
import com.example.musicwiki.data.model.tracks.Tracks
import com.example.musicwiki.data.model.tracks.TracksX
import com.example.musicwiki.data.room.AppDatabase
import com.example.musicwiki.data.room.entities.Album
import com.example.musicwiki.data.room.entities.Artist
import com.example.musicwiki.data.room.entities.Track
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.CoroutineExtensions.io
import com.example.musicwiki.utils.CoroutineExtensions.main
import com.example.musicwiki.utils.UtilExceptions

class GenreRepository(private val myApi: MyApi, private val appDatabase: AppDatabase) :
    SafeApiRequest() {
    /**
     * Stores error message
     */
    private val messageLiveData = MutableLiveData<String>()

    /**
     * Returns info of a particular genre and update room
     */
    suspend fun getGenreInfo(genreName: String): GenreInfo {
        return try {
            val genreInfo = apiRequest { myApi.getGenreInfo(genreName) }
            appDatabase.dao().insertTag(genreInfo.tag!!)
            genreInfo
        } catch (e: UtilExceptions.NoConnectivityException) {
            main { messageLiveData.value = e.message }
            val tag = appDatabase.dao().getTag(genreName)
            return GenreInfo(tag)
        } catch (e: UtilExceptions.NoInternetException) {
            main { messageLiveData.value = messageLiveData.value + "\n" + e.message }
            val tag = appDatabase.dao().getTag(genreName)
            return GenreInfo(tag)
        }
    }

    /**
     * Returns list of albums and update album items in room
     */
    suspend fun getAlbums(genreName: String): Albums {
        return try {
            val albums = apiRequest { myApi.getAlbumList(genreName) }
            io {
                for (album in albums.albums.album) {
                    appDatabase.dao()
                        .insertAlbum(Album(genreName, album.artist, album.image, album.name))
                }
            }
            albums
        } catch (e: UtilExceptions.NoConnectivityException) {
            main { messageLiveData.value = e.message }
            val albumList = appDatabase.dao().getAlbumList(genreName)
            return Albums(AlbumsX(albumList))
        } catch (e: UtilExceptions.NoInternetException) {
            main { messageLiveData.value = messageLiveData.value + "\n" + e.message }
            val albumList = appDatabase.dao().getAlbumList(genreName)
            return Albums(AlbumsX(albumList))
        }
    }

    /**
     * Returns list of artists and update artist items in room
     */
    suspend fun getArtists(genreName: String): TopArtists {
        return try {
            val topArtists = apiRequest { myApi.getArtistList(genreName) }
            io {
                for (artist in topArtists.topartists.artist) {
                    appDatabase.dao().insertArtist(Artist(genreName, artist.image, artist.name))
                }
            }
            topArtists
        } catch (e: UtilExceptions.NoConnectivityException) {
            main { messageLiveData.value = e.message }
            val artistList = appDatabase.dao().getArtistList(genreName)
            return TopArtists(TopartistsX(artistList))
        } catch (e: UtilExceptions.NoInternetException) {
            main { messageLiveData.value = messageLiveData.value + "\n" + e.message }
            val artistList = appDatabase.dao().getArtistList(genreName)
            return TopArtists(TopartistsX(artistList))
        }
    }

    /**
     * Returns list of tracks and update track items in room
     */
    suspend fun getTracks(genreName: String): Tracks {
        return try {
            val tracks = apiRequest { myApi.getTrackList(genreName) }
            io {
                for (track in tracks.tracks.track) {
                    appDatabase.dao()
                        .insertTrack(Track(genreName, track.artist, track.image, track.name))
                }
            }
            tracks
        } catch (e: UtilExceptions.NoConnectivityException) {
            main { messageLiveData.value = e.message }
            val artistList = appDatabase.dao().getTrackList(genreName)
            return Tracks(TracksX(artistList))
        } catch (e: UtilExceptions.NoInternetException) {
            main { messageLiveData.value = messageLiveData.value + "\n" + e.message }
            val artistList = appDatabase.dao().getTrackList(genreName)
            return Tracks(TracksX(artistList))
        }
    }

    /**
     * Returns reference of messageLiveData
     */
    fun getMessageLiveData(): MutableLiveData<String> {
        return messageLiveData
    }
}