package com.example.musicwiki.ui.genreInfo.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.albums.Album
import com.example.musicwiki.data.repository.GenreRepository

class AlbumViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val albumListLiveData: LiveData<List<Album>> = genreRepository.getAlbumsListLiveData()
    private val messageLiveData: LiveData<String> = genreRepository.getMessageLiveData()

    fun getAlbumsList(genreName: String) {
        genreRepository.getAlbums(genreName)
    }

    fun getAlbumListLiveData(): LiveData<List<Album>> {
        return albumListLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}