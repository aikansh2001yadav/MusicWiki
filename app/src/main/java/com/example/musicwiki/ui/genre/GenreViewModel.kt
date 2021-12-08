package com.example.musicwiki.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.genreItems.Tag
import com.example.musicwiki.data.repository.GenreItemsRepository
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreViewModel(private val genreItemsRepository: GenreItemsRepository) : ViewModel() {
    private val genreLiveData = MutableLiveData<List<Tag>>()
    private val messageLiveData = MutableLiveData<String>()

    fun getGenre() {
        try {
            CoroutineExtensions.ioThenMain({
                genreItemsRepository.getGenre()
            }, {
                genreLiveData.value = it?.toptags?.tag
            })
        } catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoConnectivityException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoInternetException) {
            messageLiveData.value = e.message
        }
    }

    fun getGenreLiveData(): LiveData<List<Tag>> {
        return genreLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}