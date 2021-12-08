package com.example.musicwiki.data.repository

import com.example.musicwiki.data.model.albums.Albums
import com.example.musicwiki.data.model.albums.AlbumsX
import com.example.musicwiki.data.model.artists.TopArtists
import com.example.musicwiki.data.model.artists.TopartistsX
import com.example.musicwiki.data.model.genreInfo.GenreInfo
import com.example.musicwiki.data.model.tracks.Tracks
import com.example.musicwiki.data.model.tracks.TracksX
import com.example.musicwiki.data.room.AppDatabase
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.UtilExceptions

class GenreRepository(private val myApi: MyApi, private val appDatabase: AppDatabase) :
    SafeApiRequest() {
//    private val messageLiveData = MutableLiveData<String>()
//    private val genreInfoLiveData = MutableLiveData<Tag>()
//    private val albumListLiveData = MutableLiveData<List<Album>>()
//    private val artistListLiveData = MutableLiveData<List<Artist>>()
//    private val trackListLiveData = MutableLiveData<List<Track>>()

//    fun getGenreInfo(genreName: String) {
//        try {
//            CoroutineExtensions.ioThenMain({ apiRequest { myApi.getGenreInfo(genreName) } }, {
//                genreInfoLiveData.value = it.tag
//                appDatabase.dao().insertTag(it.tag)
//            })
//        } catch (e: UtilExceptions.NoConnectivityException) {
//            e.printStackTrace()
//            messageLiveData.value = e.message
//        }
//    }

    suspend fun getGenreInfo(genreName: String) : GenreInfo?{
        return try{
            apiRequest { myApi.getGenreInfo(genreName) }
        }catch (e : UtilExceptions.NoConnectivityException){
            val tag = appDatabase.dao().getTag(genreName)
            return GenreInfo(tag)
        }
    }
//    fun getAlbums(genreName: String) {
//        try{
//            CoroutineExtensions.ioThenMain({ apiRequest { myApi.getAlbumList(genreName) } }, {
//                albumListLiveData.value = it.albums.album
//                io {
//                    for (album in it.albums.album) {
//                        appDatabase.dao()
//                            .insertAlbum(Album(genreName, album.artist, album.image, album.name))
//                    }
//                }
//            })
//        }catch(e : UtilExceptions.NoConnectivityException){
//            e.printStackTrace()
//            messageLiveData.value = e.message
//        }
//    }

    suspend fun getAlbums(genreName: String) : Albums{
        return try{
            apiRequest { myApi.getAlbumList(genreName) }
        }catch (e : UtilExceptions.NoConnectivityException){
            val albumList = appDatabase.dao().getAlbumList(genreName)
            return Albums(AlbumsX(albumList))
        }
    }

//    fun getArtists(genreName: String) {
//        try{
//            CoroutineExtensions.ioThenMain({ apiRequest { myApi.getArtistList(genreName) } }, {
//                artistListLiveData.value = it.topartists.artist
//                io {
//                    for (artist in it.topartists.artist) {
//                        appDatabase.dao().insertArtist(Artist(genreName, artist.image, artist.name))
//                    }
//                }
//            })
//        }catch(e : UtilExceptions.NoConnectivityException){
//            e.printStackTrace()
//            messageLiveData.value = e.message
//        }
//    }

    suspend fun getArtists(genreName: String) : TopArtists{
        return try{
            apiRequest { myApi.getArtistList(genreName) }
        }catch (e : UtilExceptions.NoConnectivityException){
            val artistList = appDatabase.dao().getArtistList(genreName)
            return TopArtists(TopartistsX(artistList))
        }
    }

//    fun getTracks(genreName: String){
//        try{
//            CoroutineExtensions.ioThenMain({apiRequest { myApi.getTrackList(genreName) }}, {
//                trackListLiveData.value = it.tracks.track
//                io {
//                    for (track in it.tracks.track) {
//                        appDatabase.dao().insertTrack(Track(genreName, track.artist, track.image, track.name))
//                    }
//                }
//            })
//        }catch (e : UtilExceptions.NoConnectivityException){
//            e.printStackTrace()
//            messageLiveData.value = e.message
//        }
//    }

    suspend fun getTracks(genreName: String) : Tracks{
        return try{
            apiRequest { myApi.getTrackList(genreName) }
        }catch (e : UtilExceptions.NoConnectivityException){
            val artistList = appDatabase.dao().getTrackList(genreName)
            return Tracks(TracksX(artistList))
        }
    }


//    fun getGenreInfoLiveData(): MutableLiveData<Tag> {
//        return genreInfoLiveData
//    }
//
//    fun getMessageLiveData(): MutableLiveData<String> {
//        return messageLiveData
//    }
//
//    fun getAlbumsListLiveData(): MutableLiveData<List<Album>> {
//        return albumListLiveData
//    }
//
//    fun getArtistListLiveData(): MutableLiveData<List<Artist>> {
//        return artistListLiveData
//    }
//
//    fun getTrackListLiveData() : MutableLiveData<List<Track>>{
//        return trackListLiveData
//    }
}