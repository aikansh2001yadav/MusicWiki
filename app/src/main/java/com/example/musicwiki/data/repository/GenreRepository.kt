package com.example.musicwiki.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreRepository(private val myApi: MyApi) : SafeApiRequest() {
    private val messageLiveData = MutableLiveData<String>()
    private val genreInfoLiveData = MutableLiveData<Tag>()
    private val albumListLiveData = MutableLiveData<List<Album>>()

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
        CoroutineExtensions.ioThenMain({apiRequest { myApi.getAlbumList(genreName) }}, {
            albumListLiveData.value = it.albums.album
        })
    }

    fun getGenreInfoLiveData(): MutableLiveData<Tag> {
        return genreInfoLiveData
    }

    fun getMessageLiveData(): MutableLiveData<String> {
        return messageLiveData
    }

    fun getAlbumsListLiveData() : MutableLiveData<List<Album>>{
        return albumListLiveData
    }
}