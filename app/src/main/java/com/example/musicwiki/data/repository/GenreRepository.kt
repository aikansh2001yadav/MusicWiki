package com.example.musicwiki.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.data.model.tracks.Track
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreRepository(private val myApi: MyApi) : SafeApiRequest() {
    private val messageLiveData = MutableLiveData<String>()
    private val genreInfoLiveData = MutableLiveData<Tag>()
    private val albumListLiveData = MutableLiveData<List<Album>>()
    private val artistListLiveData = MutableLiveData<List<Artist>>()
    private val trackListLiveData = MutableLiveData<List<Track>>()

    fun getGenreInfo(genreName: String) {
        try {
            CoroutineExtensions.ioThenMain({ apiRequest { myApi.getGenreInfo(genreName) } }, {
                genreInfoLiveData.value = it.tag
            })
        } catch (e: UtilExceptions.NetworkException) {
            e.printStackTrace()
            messageLiveData.value = e.message
        }
    }

    fun getAlbums(genreName: String) {
        CoroutineExtensions.ioThenMain({ apiRequest { myApi.getAlbumList(genreName) } }, {
            albumListLiveData.value = it.albums.album
        })
    }

    fun getArtists(genreName: String) {
        CoroutineExtensions.ioThenMain({ apiRequest { myApi.getArtistList(genreName) } }, {
            artistListLiveData.value = it.topartists.artist
        })
    }

    fun getTracks(genreName: String){
        CoroutineExtensions.ioThenMain({apiRequest { myApi.getTrackList(genreName) }}, {
            trackListLiveData.value = it.tracks.track
        })
    }
    fun getGenreInfoLiveData(): MutableLiveData<Tag> {
        return genreInfoLiveData
    }

    fun getMessageLiveData(): MutableLiveData<String> {
        return messageLiveData
    }

    fun getAlbumsListLiveData(): MutableLiveData<List<Album>> {
        return albumListLiveData
    }

    fun getArtistListLiveData(): MutableLiveData<List<Artist>> {
        return artistListLiveData
    }

    fun getTrackListLiveData() : MutableLiveData<List<Track>>{
        return trackListLiveData
    }
}