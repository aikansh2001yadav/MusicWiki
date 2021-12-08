package com.example.musicwiki.ui.genreInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.model.genreInfo.Tag
import com.example.musicwiki.data.repository.GenreRepository
import com.example.musicwiki.utils.CoroutineExtensions
import com.example.musicwiki.utils.UtilExceptions

class GenreInfoViewModel(private val genreRepository: GenreRepository) : ViewModel() {

    private val genreInfoLiveData = MutableLiveData<Tag>()
    private var messageLiveData = MutableLiveData<String>()

    fun getGenreInfo(genreName: String) {
        try{
            CoroutineExtensions.ioThenMain({ genreRepository.getGenreInfo(genreName) }, {
                genreInfoLiveData.value = it?.tag
            })
        }catch (e: UtilExceptions.ApiException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoConnectivityException) {
            messageLiveData.value = e.message
        } catch (e: UtilExceptions.NoInternetException) {
            messageLiveData.value = e.message
        }
    }

    fun getGenreInfoLiveData(): LiveData<Tag> {
        return genreInfoLiveData
    }

    fun getMessageLiveData(): LiveData<String> {
        return messageLiveData
    }
}