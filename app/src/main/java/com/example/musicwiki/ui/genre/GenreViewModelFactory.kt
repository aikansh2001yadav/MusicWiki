package com.example.musicwiki.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.data.repository.GenreItemsRepository

class GenreViewModelFactory(private val itemsRepository: GenreItemsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenreViewModel(itemsRepository) as T
    }
}