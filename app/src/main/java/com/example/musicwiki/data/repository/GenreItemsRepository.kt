package com.example.musicwiki.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.data.model.genreItems.Tag
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreItemsRepository(private val myApi: MyApi) : SafeApiRequest() {

    private val messageLiveData = MutableLiveData<String>()
    private val genreLiveData = MutableLiveData<List<Tag>>()

    fun getGenre() {
        try {
            CoroutineExtensions.ioThenMain({
                apiRequest { myApi.getGenreList() }
            }, {
                genreLiveData.value = it.toptags.tag
            })
        } catch (e: UtilExceptions.NetworkException) {
            messageLiveData.value = e.message
        }
    }

    fun getGenreLiveData(): MutableLiveData<List<Tag>> {
        return genreLiveData
    }

    fun getMessageLiveData() : MutableLiveData<String>{
        return messageLiveData
    }
}