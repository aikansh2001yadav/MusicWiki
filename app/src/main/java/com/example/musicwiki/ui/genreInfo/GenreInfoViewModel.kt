package com.example.musicwiki.ui.genreInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.data.room.entities.Tag
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreInfoViewModel(private val genreRepository: GenreRepository) : ViewModel() {

    private val genreInfoLiveData = MutableLiveData<Tag>()

    fun getGenreInfo(genreName: String) {
        try{
            CoroutineExtensions.ioThenMain({ genreRepository.getGenreInfo(genreName) }, {
                if(it.tag != null){
                    genreInfoLiveData.value = it.tag!!
                }
            })
        }catch (e: UtilExceptions.ApiException) {
            e.printStackTrace()
        }
    }

    fun getGenreInfoLiveData(): LiveData<Tag> {
        return genreInfoLiveData
    }
}