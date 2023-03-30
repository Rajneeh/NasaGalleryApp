package com.nasa.gallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasa.gallery.repository.NasaRepository

class ImageViewModelFactory(private val repository: NasaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageViewModel(repository) as T
    }
}