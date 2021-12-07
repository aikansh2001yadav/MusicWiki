package com.example.musicwiki.ui.genreInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.data.repository.GenreRepository

class GenreInfoViewModelFactory(private val genreRepository: GenreRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenreInfoViewModel(genreRepository) as T
    }
}