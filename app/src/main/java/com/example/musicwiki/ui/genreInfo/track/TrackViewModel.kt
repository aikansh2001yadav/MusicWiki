package com.example.musicwiki.ui.genreInfo.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.tracks.Track
import com.example.musicwiki.data.repository.GenreRepository

class TrackViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    private val trackListLiveData:LiveData<List<Track>> = genreRepository.getTrackListLiveData()
    private val messageLiveData:LiveData<String> = genreRepository.getMessageLiveData()

    fun getTracks(genreName : String){
        genreRepository.getTracks(genreName)
    }

    fun getTrackListLiveData() : LiveData<List<Track>>{
        return trackListLiveData
    }

    fun getMessageLiveData() : LiveData<String>{
        return messageLiveData
    }
}