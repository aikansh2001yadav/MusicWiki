package com.example.musicwiki.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.repository.GenreItemsRepository
import com.example.musicwiki.data.room.entities.Genre
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreViewModel(private val genreItemsRepository: GenreItemsRepository) : ViewModel() {
    private val genreLiveData = MutableLiveData<List<Genre>>()
    private val messageLiveData = genreItemsRepository.getMessageLiveData()

    fun getGenre() {
        try {
            CoroutineExtensions.ioThenMain({
                genreItemsRepository.getGenre()
            }, {
                genreLiveData.value = it.togGenreItems?.genre
            })
        } catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = messageLiveData.value + "\n" + e.message
        }
    }

    fun getGenreLiveData(): LiveData<List<Genre>> {
        return genreLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}