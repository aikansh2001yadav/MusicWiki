package com.example.musicwiki.ui.genreInfo.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.data.repository.GenreRepository

class AlbumViewModelFactory(private val genreRepository: GenreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(genreRepository) as T
    }
}