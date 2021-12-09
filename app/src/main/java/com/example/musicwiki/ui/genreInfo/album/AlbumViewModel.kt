package com.example.musicwiki.ui.genreInfo.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.data.room.entities.Album
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class AlbumViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val albumListLiveData = MutableLiveData<List<Album>>()
    private val messageLiveData = genreRepository.getMessageLiveData()

    fun getAlbumsList(genreName: String) {
        try{
            CoroutineExtensions.ioThenMain({ genreRepository.getAlbums(genreName) }, {
                albumListLiveData.value = it?.albums?.album
            })
        }catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = messageLiveData.value + "\n" + e.message
        }
    }

    fun getAlbumListLiveData(): LiveData<List<Album>> {
        return albumListLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}