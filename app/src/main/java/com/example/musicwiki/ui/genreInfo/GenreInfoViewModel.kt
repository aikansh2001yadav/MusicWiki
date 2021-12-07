package com.example.musicwiki.ui.genreInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.data.repository.GenreRepository

class GenreInfoViewModel(private val genreRepository: GenreRepository) : ViewModel() {

    private var genreInfoLiveData: LiveData<Tag> = genreRepository.getGenreInfoLiveData()
    private var messageLiveData: LiveData<String> = genreRepository.getMessageLiveData()

    fun getGenreInfo(genreName : String){
        genreRepository.getGenreInfo(genreName)
    }
    fun getGenreInfoLiveData(): LiveData<Tag> {
        return genreInfoLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}