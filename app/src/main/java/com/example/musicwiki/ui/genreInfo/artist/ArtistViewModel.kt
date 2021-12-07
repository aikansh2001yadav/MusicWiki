package com.example.musicwiki.ui.genreInfo.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.repository.GenreRepository

class ArtistViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val artistListLiveData: LiveData<List<Artist>> = genreRepository.getArtistListLiveData()
    private val messageLiveData: LiveData<String> = genreRepository.getMessageLiveData()

    fun getArtistList(genreName: String) {
        genreRepository.getArtists(genreName)
    }

    fun getArtistListLiveData(): LiveData<List<Artist>> {
        return artistListLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}