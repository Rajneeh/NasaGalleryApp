package com.nasa.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasa.gallery.model.ImageDataModel
import com.nasa.gallery.repository.NasaRepository
import com.nasa.gallery.utils.Result
import kotlinx.coroutines.launch

class ImageViewModel(private val repository: NasaRepository) : ViewModel() {
    private val _imagesListLiveData = MutableLiveData<List<ImageDataModel>>()
    val imageListLiveData: LiveData<List<ImageDataModel>> get() = _imagesListLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorMsgLiveData = MutableLiveData<String>()
    val errorMsgLiveData: LiveData<String> get() = _errorMsgLiveData

    fun fetchImages() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            when (val response = repository.getImages()) {
                is Result.Success -> {
                    _loadingLiveData.postValue(false)
                    val list = response.data
                    _imagesListLiveData.postValue(list)
                }
                is Result.Error -> {
                    _errorMsgLiveData.postValue(response.exception.message)
                }
            }
        }
    }
}