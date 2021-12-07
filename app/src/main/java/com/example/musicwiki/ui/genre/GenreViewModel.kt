package com.example.musicwiki.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.genreItems.Tag
import com.example.musicwiki.data.repository.GenreItemsRepository

class GenreViewModel(private val genreItemsRepository: GenreItemsRepository) : ViewModel() {
    private val genreLiveData: LiveData<List<Tag>>
    private val messageLiveData: LiveData<String>

    init {
        genreLiveData = genreItemsRepository.getGenreLiveData()
        messageLiveData = genreItemsRepository.getMessageLiveData()
    }

    fun getGenre() {
        genreItemsRepository.getGenre()
    }

    fun getGenreLiveData(): LiveData<List<Tag>> {
        return genreLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}