package com.example.musicwiki.data.repository

import com.example.musicwiki.data.model.genreItems.GenreItems
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.UtilExceptions

class GenreItemsRepository(private val myApi: MyApi) : SafeApiRequest() {

//    private val messageLiveData = MutableLiveData<String>()
//    private val genreLiveData = MutableLiveData<List<Tag>>()

//    fun getGenre() {
//        try {
//            CoroutineExtensions.ioThenMain({
//                apiRequest { myApi.getGenreList() }
//            }, {
//                genreLiveData.value = it.toptags.tag
//            })
//        } catch (e: UtilExceptions.NoConnectivityException) {
//            messageLiveData.value = e.message
//        }
//    }

    suspend fun getGenre() : GenreItems? = try {
        apiRequest { myApi.getGenreList() }
    } catch (e: UtilExceptions.NoConnectivityException) {
        null
    }
//
//    fun getGenreLiveData(): MutableLiveData<List<Tag>> {
//        return genreLiveData
//    }
//
//    fun getMessageLiveData() : MutableLiveData<String>{
//        return messageLiveData
//    }
}