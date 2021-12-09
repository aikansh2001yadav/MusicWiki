package com.example.musicwiki.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.data.model.genreItems.GenreItems
import com.example.musicwiki.data.model.genreItems.TogGenreItems
import com.example.musicwiki.data.room.AppDatabase
import com.example.musicwiki.network.MyApi
import com.example.musicwiki.network.SafeApiRequest
import com.example.musicwiki.utils.CoroutineExtensions.main
import com.example.musicwiki.utils.UtilExceptions

class GenreItemsRepository(private val myApi: MyApi, private val appDatabase: AppDatabase) :
    SafeApiRequest() {
    /**
     * Stores error message
     */
    private val messageLiveData = MutableLiveData<String>()

    /**
     * Return genreItems and update genre items in room
     */
    suspend fun getGenre(): GenreItems = try {
        val genreItems = apiRequest { myApi.getGenreList() }
        appDatabase.dao().insertGenreList(genreItems.togGenreItems!!.genre)
        genreItems
    } catch (e: UtilExceptions.NoConnectivityException) {
        main { messageLiveData.value = e.message }
        val genreList = appDatabase.dao().getGenreList()
        GenreItems(TogGenreItems(genreList))
    } catch (e: UtilExceptions.NoInternetException) {
        main { messageLiveData.value = messageLiveData.value + "\n" + e.message }
        val genreList = appDatabase.dao().getGenreList()
        GenreItems(TogGenreItems(genreList))
    }

    /**
     * Returns reference of messageLiveData
     */
    fun getMessageLiveData(): MutableLiveData<String> {
        return messageLiveData
    }
}