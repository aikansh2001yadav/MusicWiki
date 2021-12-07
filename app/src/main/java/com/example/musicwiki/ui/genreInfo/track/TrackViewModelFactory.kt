package com.example.musicwiki.ui.genreInfo.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.data.repository.GenreRepository

class TrackViewModelFactory(private val genreRepository: GenreRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrackViewModel(genreRepository) as T
    }
}