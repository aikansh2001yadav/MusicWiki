package com.example.musicwiki.ui.genreInfo.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.artists.Artist
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class ArtistViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val artistListLiveData = MutableLiveData<List<Artist>>()
    private val messageLiveData = MutableLiveData<String>()

    fun getArtistList(genreName: String) {
        try{
            CoroutineExtensions.ioThenMain({ genreRepository.getArtists(genreName) }, {
                artistListLiveData.value = it?.topartists?.artist
            })
        }catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoConnectivityException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoInternetException) {
            messageLiveData.value = e.message
        }
    }

    fun getArtistListLiveData(): LiveData<List<Artist>> {
        return artistListLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}