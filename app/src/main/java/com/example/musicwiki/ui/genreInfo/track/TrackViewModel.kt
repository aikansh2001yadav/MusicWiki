package com.example.musicwiki.ui.genreInfo.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.tracks.Track
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class TrackViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val trackListLiveData = MutableLiveData<List<Track>>()
    private val messageLiveData = MutableLiveData<String>()

    fun getTracks(genreName : String){
        try{
            CoroutineExtensions.ioThenMain({ genreRepository.getTracks(genreName) }, {
                trackListLiveData.value = it?.tracks?.track
            })
        }catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoConnectivityException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoInternetException) {
            messageLiveData.value = e.message
        }
    }

    fun getTrackListLiveData() : LiveData<List<Track>>{
        return trackListLiveData
    }

    fun getMessageLiveData() : LiveData<String>{
        return messageLiveData
    }
}