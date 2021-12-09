package com.example.musicwiki.ui.genreInfo.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.data.db.entities.Artist
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class ArtistViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val artistListLiveData = MutableLiveData<List<Artist>>()
    private val messageLiveData = genreRepository.getMessageLiveData()

    fun getArtistList(genreName: String) {
        try{
            CoroutineExtensions.ioThenMain({ genreRepository.getArtists(genreName) }, {
                artistListLiveData.value = it?.topartists?.artist
            })
        }catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = messageLiveData.value + "\n" + e.message
        }
    }

    fun getArtistListLiveData(): LiveData<List<Artist>> {
        return artistListLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}