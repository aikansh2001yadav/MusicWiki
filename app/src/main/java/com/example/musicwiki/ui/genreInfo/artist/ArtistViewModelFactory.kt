package com.example.musicwiki.ui.genreInfo.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.data.repository.GenreRepository

class ArtistViewModelFactory(private val genreRepository: GenreRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(genreRepository) as T
    }
}